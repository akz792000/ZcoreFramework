package org.zcoreframework.component.mapping.target.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.zcoreframework.base.core.CrudTemplate;
import org.zcoreframework.base.core.Crudable;
import org.zcoreframework.base.dao.DefaultEntityManager;
import org.zcoreframework.base.exception.ClientException;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.component.mapping.MapperModel;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.core.FieldBeanWrapperImpl;
import org.zcoreframework.mapping.mapper.ModelFieldBeanMapperImpl;

public class GridDetailEntityMapper extends ModelFieldBeanMapperImpl<Set<Object>, MapperModel> {
	
	public GridDetailEntityMapper(FieldBeanWrapper mappingBeanWrapper, MapperModel model) {
		super(mappingBeanWrapper, model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setPropertyValue(Object object) throws Exception {
		
		// get basic entity manager
		DefaultEntityManager entityManager = (DefaultEntityManager) ApplicationContextUtils.createBean(DefaultEntityManager.class);
			
		// set new value if empty
		if (getValue() == null) {
			super.setPropertyValue(new HashSet<Object>());
		}		
		
		// get class of object
		ParameterizedType objectType = (ParameterizedType) getWrappedClass().getDeclaredField(getName()).getGenericType();
		Class<Object> clazz = (Class<Object>) objectType.getActualTypeArguments()[0];
		
		// CRUD template activate 
		new CrudTemplate<>(clazz, getValue(), (List<Map<String, Object>>) object)
			.execute(new Crudable<Object>() {
				
				private String master;
			
				@Override
				public void persist(Object entity, Map<String, Object> selected) throws Exception {
					merge(entity, selected);
				}
				
				@Override
				public void merge(Object entity, Map<String, Object> selected) throws Exception {
					
					// set master of entity
					if (master == null) {
						for (Field field : entity.getClass().getDeclaredFields()) {
							if (field.getType().equals(getWrappedClass())) {
								master = field.getName();
								break;
							}
						}
					}
					
					// set master entity
					BeanWrapper beanWrapper = new BeanWrapperImpl(entity);
					beanWrapper.setPropertyValue(master, getWrappedInstance());
					
					// set property value
					Set<String> temp = new HashSet<>();
					for (Entry<String, Object> entry : selected.entrySet()) {
						String name = entry.getKey();
						Object val = selected.get(name);
						if (name.equals("status") || (name.equals("id") && Long.parseLong(val.toString()) < 0)) {
							continue;
						}
						if (name.indexOf(".") != -1) {
							name = name.split("\\.")[0];
							/*
							 * just set for property like [property].id
							 */
							if (temp.contains(name)) {
								continue;
							}
							temp.add(name);
							val = entityManager.getEntityManager().find(beanWrapper.getPropertyType(name), (Long) selected.get(name + ".id"));
							if (val == null) {
								ClientException.deliver("Entity does not exist", new Object[] { name });
							}
						}	
						// set
						if (beanWrapper.getPropertyType(name).isAssignableFrom(Set.class)) {
							new GridDetailEntityMapper(new FieldBeanWrapperImpl(entity, name), new MapperModel()).setPropertyValue(val);
						} else {
							beanWrapper.setPropertyValue(name, val);
						}
					}
																			
				}
				
				@Override
				public Boolean remove(Object entity) throws Exception {
					return true;				
				}		
	
				@Override
				public void commit() throws ClientException {
					entityManager.getEntityManager().flush();			
				}
				
			});	
	}

}