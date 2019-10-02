/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;
import org.zcoreframework.mapping.core.BeanMapper.MappingDirection;

public class BaseModel implements Model {
	
	private MappingDirection mappingDirection;
	
	@Override
	public MappingDirection getMappingDirection() {
		return mappingDirection;
	}
	
	public void setMappingDirection(MappingDirection mappingDirection) {
		this.mappingDirection = mappingDirection;
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
	
}
