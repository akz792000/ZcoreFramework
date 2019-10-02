/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zcoreframework.base.util.ReflectionUtils;

public class CrudTemplate<T> {

	private final Class<T> clazz;

	private Set<T> source;

	private final List<Map<String, Object>> selectedItems;

	public Set<T> getSource() {
		return source;
	}

	public List<Map<String, Object>> getSelectedItems() {
		return selectedItems;
	}

	public CrudTemplate(Class<T> clazz, Set<T> source, List<Map<String, Object>> selectedItems) {
		this.clazz = clazz;
		this.source = source;
		this.selectedItems = selectedItems;
	}

	public void execute(Crudable<T> crudable) throws Exception {

		// order by status field
		Collections.sort(selectedItems, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> fst, Map<String, Object> snd) {
				return (int) (((Long) fst.get("status")) - ((Long) snd.get("status")));
			}

		});

		// set user roles
		for (Map<String, Object> selected : selectedItems) {

			Iterator<T> iterator;
			T entity = null;
			Method method;			
			int status = (int) ((long) selected.get("status"));

			switch (status) {
			// remove
			case -1:
				iterator = source.iterator();
				while (iterator.hasNext()) {
					entity = (T) iterator.next();
					method = ReflectionUtils.findMethod(entity.getClass(), "getId");
					try {
						if (method.invoke(entity).equals(Long.parseLong(selected.get("id").toString()))) {
							if (crudable.remove(entity)) {
								iterator.remove();
								break;
							}
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						ReflectionUtils.handleReflectionException(e);
					}
				}
				break;

			// persist
			case 1:
				try {
					entity = clazz.newInstance();
					crudable.persist(entity, selected);
					source.add(entity);
				} catch (InstantiationException | IllegalAccessException e) {
					ReflectionUtils.handleReflectionException(e);
				}
				break;

			// merge
			case 2:
				for (iterator = source.iterator(); iterator.hasNext();) {
					entity = (T) iterator.next();
					method = ReflectionUtils.findMethod(entity.getClass(), "getId");
					try {
						if (method.invoke(entity).equals(Long.parseLong(selected.get("id").toString()))) {
							crudable.merge(entity, selected);
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						ReflectionUtils.handleReflectionException(e);
					}
				}
				break;
			}

			// commit
			if (status != 0) {
				crudable.commit();
			}

		}
	}

}