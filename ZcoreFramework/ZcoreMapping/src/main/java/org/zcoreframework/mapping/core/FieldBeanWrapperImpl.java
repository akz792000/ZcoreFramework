/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.core;

import org.springframework.beans.BeanWrapperImpl;

public class FieldBeanWrapperImpl extends BeanWrapperImpl implements FieldBeanWrapper {
	
	private String name;
	
	@Override	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public FieldBeanWrapperImpl(Object object) {
		setWrappedInstance(object);
	}
	
	public FieldBeanWrapperImpl(Object object, String name) {
		setWrappedInstance(object);
		this.name = name;
	}	
	
	@Override
	public Object getPropertyValue() throws Exception {
		return getPropertyValue(name);
	}
	
	@Override
	public Class<?> getPropertyType() throws Exception {
		return getPropertyType(name);
	}
	
	@Override
	public void setPropertyValue(Object value) throws Exception {		
		setPropertyValue(name, value);
	}
	
}
