/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.core;

import org.springframework.beans.BeanWrapper;

public interface FieldBeanWrapper extends BeanWrapper {
	
	String getName();
	
	void setName(String name);
	
	Object getPropertyValue() throws Exception;
		
	Class<?> getPropertyType() throws Exception;
		
	void setPropertyValue(Object value) throws Exception;

}
