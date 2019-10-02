/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.core;

public interface ExaminBeanWrapper {
	
	public Object getWrappedInstance();
	
	public Object getPropertyValue();
	
	public Class<?> getPropertyType();

}
