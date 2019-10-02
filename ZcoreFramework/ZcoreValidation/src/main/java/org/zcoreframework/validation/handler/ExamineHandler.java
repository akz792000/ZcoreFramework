/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;
import org.zcoreframework.validation.core.ExaminBeanWrapper;
import org.zcoreframework.validation.core.ValidateBeanWrapper;
import org.zcoreframework.validation.model.ExamineModel;

public class ExamineHandler extends AbstractHandler<ExamineModel<String>, ExaminBeanWrapper> {
	
	public static final class DEFAULT {}
	
	public ExamineHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);		
	}	
	
	@Override
	protected ExaminBeanWrapper prepareObject(final ValidateBeanWrapper beanWrapper, final Field field) {
		return new ExaminBeanWrapper() {
			
			@Override
			public Object getWrappedInstance() {
				return beanWrapper.getWrappedInstance();
			}
			
			@Override
			public Object getPropertyValue() {
				return ReflectionUtils.getField(field, beanWrapper.getWrappedInstance());
			}

			@Override
			public Class<?> getPropertyType() {			
				return field.getType();
			}
			
		};
	}
	
	@Override
	protected boolean doValidate(ExaminBeanWrapper object) {
		Method method = null;
		Object wrappedInstance = object.getWrappedInstance();
		Object propertyValue = object.getPropertyValue();
		Class<?> propertyType = object.getPropertyType();
		// call own method 
		if (getModel().getClazz().equals(DEFAULT.class)) {
			method = ReflectionUtils.findMethod(wrappedInstance.getClass(), getModel().getValue(), propertyType);
			ReflectionUtils.makeAccessible(method);
			return (boolean) ReflectionUtils.invokeMethod(method, wrappedInstance, propertyValue);
		// call static method of a class	
		} else {
			method = ReflectionUtils.findMethod(getModel().getClazz(), getModel().getValue(), ExaminBeanWrapper.class);
			ReflectionUtils.makeAccessible(method);
			return (boolean) ReflectionUtils.invokeMethod(method, null, object);		
		}
	}

}
