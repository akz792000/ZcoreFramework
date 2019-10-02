/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.ReflectionUtils;
import org.zcoreframework.validation.core.ValidateBeanWrapper;
import org.zcoreframework.validation.model.Model;

public abstract class AbstractHandler<M extends Model, V> implements Handler<M> {
	
	private final M model;
	
	private final boolean nullable;
	
	@Override
	public M getModel() {
		return model;
	}
	
	@SuppressWarnings("unchecked")
	public AbstractHandler(Annotation annotation, boolean nullable) throws InstantiationException, IllegalAccessException {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		Type type = genericSuperclass.getActualTypeArguments()[0];
		if (type instanceof ParameterizedType) {
			type = ((ParameterizedType) type).getRawType();
		}
		model = ((Class<M>) type).newInstance();
		model.setProperties(annotation);
		this.nullable = nullable;
	}

	protected boolean preValidate(EvaluationContext evaluationContext, String applyIf) {
		ExpressionParser parser = new SpelExpressionParser();
		return parser.parseExpression(applyIf).getValue(evaluationContext, Boolean.class);		
	}
	
	protected abstract boolean doValidate(V object);
	
	@SuppressWarnings("unchecked")
	protected V prepareObject(final ValidateBeanWrapper validateBeanWrapper, final Field field) { 		
		return (V) ReflectionUtils.getField(field, validateBeanWrapper.getWrappedInstance());
	}
	
	@Override
	public boolean validate(ValidateBeanWrapper validateBeanWrapper, Field field) {
		String applyIf = getModel().getApplyIf();
		boolean result = applyIf.isEmpty() ? true : preValidate(validateBeanWrapper.getEvaluationContext(), applyIf);
		if (result) {
			V object = prepareObject(validateBeanWrapper, field);
			if (object == null && nullable) {
				return true;
			}
			return doValidate(object);
		} else {
			return true;
		}
	}
	
}
