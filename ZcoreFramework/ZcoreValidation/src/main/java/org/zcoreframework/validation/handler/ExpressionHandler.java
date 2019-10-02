/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.ReflectionUtils;
import org.zcoreframework.validation.core.ValidateBeanWrapper;
import org.zcoreframework.validation.model.ValueModelNoArgs;

public class ExpressionHandler extends AbstractHandler<ValueModelNoArgs<String>, EvaluationContext> {
	
	public ExpressionHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);		
	}	
	
	@Override
	protected EvaluationContext prepareObject(final ValidateBeanWrapper beanWrapper, final Field field) {
		EvaluationContext evaluationContext = beanWrapper.getEvaluationContext();
		evaluationContext.setVariable("self", ReflectionUtils.getField(field, beanWrapper.getWrappedInstance()));
		return beanWrapper.getEvaluationContext();
	}

	@Override
	protected boolean doValidate(EvaluationContext object) {
		ExpressionParser parser = new SpelExpressionParser();		
		return parser.parseExpression(getModel().getValue()).getValue(object, Boolean.class);
	}

}