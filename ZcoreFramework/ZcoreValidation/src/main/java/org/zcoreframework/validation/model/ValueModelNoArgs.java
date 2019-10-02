/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.model;

public class ValueModelNoArgs<T> extends ValueModel<T> {
		
	@Override
	public Object[] getArgs() {
		return null;
	}

}
