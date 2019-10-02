/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.model;

import java.io.Serializable;

public abstract class AbstractActionable<ID extends Serializable> implements Actionable<ID> {

	private Boolean validate = true;
	
	@Override
	public Boolean getValidate() {
		return validate;
	}
	
	public void setValidate(Boolean validate) {
		this.validate = validate;
	}
	
}
