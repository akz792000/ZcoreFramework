/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.core;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class ValidateBeanWrapperImpl implements ValidateBeanWrapper {
	
	private Object object;
	
	private EvaluationContext evaluationContext;
	
	@Override
	public Object getWrappedInstance() {
		return object;
	}
	
	@Override
	public EvaluationContext getEvaluationContext() {
		if (evaluationContext == null) {
			evaluationContext = new StandardEvaluationContext(object);
		}			
		return evaluationContext;
	}
	
	public ValidateBeanWrapperImpl(Object object) {
		this.object = object;
	}
	
}
