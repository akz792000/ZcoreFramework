/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map;

import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;

public abstract class AbstractMapEntityDAOImpl<M, E> extends AbstractPyramidDAOImpl<M> implements GenericMapDAO<M, E> {
						
	protected Object bind(Class<?> clazz, List<JoinProperty> properties, BeanWrapper beanWrapper) {
		BeanWrapper clazzWrapper;
		try {
			clazzWrapper = new BeanWrapperImpl(clazz.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
		for (JoinProperty property : properties) {
			String name = property.getName();
			Object value = beanWrapper.getPropertyValue(name);
			if (property.getProperties() != null) {
				value = bind(property.getClazz(), property.getProperties(), new BeanWrapperImpl(value));
			}
			clazzWrapper.setPropertyValue(name, value);
		}		
		return clazzWrapper.getWrappedInstance();
	}

}