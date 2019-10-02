/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.zcoreframework.base.component.AbstractComponentImpl;

public class SimpleMapEntityTarget extends AbstractComponentImpl {
	
	private Class<?> entity;
	
	private Class<?> model;
	
	private String key;
	
	private String join;
	
	private String order;
	
	private List<JoinProperty> properties;
		
	public Class<?> getEntity() {
		return entity;
	}
	
	public void setEntity(Class<?> entity) {
		this.entity = entity;
	}
	
	public Class<?> getModel() {
		return model;
	}
	
	public void setModel(Class<?> model) {
		this.model = model;
	}

	public List<JoinProperty> getProperties() {
		return properties;
	}
	
	public void setProperties(List<JoinProperty> properties) {
		this.properties = properties;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String getJoin() {
		return join;
	}
	
	public void setJoin(String join) {
		this.join = join;
	}
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
	public List<JoinProperty> getRecursiveProperties(Class<?> clazz) {
		List<JoinProperty> recursiveProperties = new LinkedList<>();
		do {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				Join join = field.getAnnotation(Join.class);
				if (join != null) {
					if (join.recursive()) {
						recursiveProperties.add(new JoinProperty(field.getName(), field.getType(), getRecursiveProperties(field.getType())));
					} else {
						recursiveProperties.add(new JoinProperty(field.getName()));
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
		while (clazz != null && clazz != Object.class);
		return recursiveProperties;
	}
	
	@Override
	public void init() {
		properties = getRecursiveProperties(getModel());
	}

}
