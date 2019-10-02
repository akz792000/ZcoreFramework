/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.model;

public class ExamineModel<T> extends ValueModel<T> {
	
	private Class<?> clazz;
	
	public Class<?> getClazz() {
		return clazz;
	}
	
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
		
}
