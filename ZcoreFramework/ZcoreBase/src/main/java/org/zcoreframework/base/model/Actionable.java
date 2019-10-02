/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.model;

import java.io.Serializable;

public interface Actionable<ID extends Serializable> {
	
	IdField<ID> getIdField();
	
	String getAction();
	
	Boolean getValidate();

}
