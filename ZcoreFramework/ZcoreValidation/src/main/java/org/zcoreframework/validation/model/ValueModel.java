/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.model;

public class ValueModel<T> extends BaseModel {
	
	private T value;
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[] { getValue() };
	}

}
