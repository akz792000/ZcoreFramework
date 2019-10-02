/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;

public class BaseModel implements Model {
	
	private String errorCode;
	
	private String applyIf;
	
	private boolean rejectable = true;
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getApplyIf() {
		return applyIf;
	}
	
	public void setApplyIf(String applyIf) {
		this.applyIf = applyIf;
	}
	
	public boolean isRejectable() {
		return rejectable;
	}
	
	public void setRejectable(boolean rejectable) {
		this.rejectable = rejectable;
	}
	
	@Override
	public void setProperties(Annotation annotation)  {	
		if (annotation != null) {
			for (Method method : annotation.annotationType().getDeclaredMethods()) {
				Field field = ReflectionUtils.findField(getClass(), method.getName());
				ReflectionUtils.makeAccessible(field);
				Object value = ReflectionUtils.invokeMethod(method, annotation);
				ReflectionUtils.setField(field, this, value);
			}
		}
	}
	
	@Override
	public Object[] getArgs() {
		return null;
	}

}
