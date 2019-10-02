/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.core;

import org.springframework.expression.EvaluationContext;

public interface ValidateBeanWrapper {
	
	public EvaluationContext getEvaluationContext();
	
	public Object getWrappedInstance();

}
