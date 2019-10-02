/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.annotation.ServiceActionParam;
import org.zcoreframework.base.core.ActionMethod;
import org.zcoreframework.base.core.ActionMethodImpl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

public abstract class ReflectionUtils extends org.springframework.util.ReflectionUtils {
	
	public static Method findMethod(Class<?> clazz, String name, Map<String, Object> serviceActionParam, Object[] args) {	
		Assert.notNull(clazz, "Class must not be null");
		Assert.notNull(name, "Method name must not be null");
		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());			
			for (Method method : methods) {
				if (name.equals(method.getName()) && (method.getParameterTypes().length == serviceActionParam.size())) {
					int j = 0;
					for (int i = 0; i < method.getParameterTypes().length; i++) {
						MethodParameter methodParam = new MethodParameter(method, i);
						for (Annotation annotation : methodParam.getParameterAnnotations()) {
							if (ServiceActionParam.class.isInstance(annotation)) {
								args[i] = serviceActionParam.get(AnnotationUtils.getValue(annotation));
								if (args[i] == null) {
									args[i] = serviceActionParam.get("arg" + i);
								}
								j++;
							}
						}
					}
					if (j == serviceActionParam.size()) {
						return method;
					}					
				}				
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
	
	public static Method findMethod(Class<?> clazz, String name, Object[] args) {		
		Assert.notNull(clazz, "Class must not be null");
		Assert.notNull(name, "Method name must not be null");
		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());			
			for (Method method : methods) {
				if (name.equals(method.getName()) && 
						(args == null || method.getParameterTypes().length == args.length)) 					
					return method;					
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}	
	
	public static List<String> getMethods(Class<?> clazz, Class<? extends Annotation> annotationClazz) {
		List<String> result = new ArrayList<String>();
		Assert.notNull(clazz, "Class must not be null");
		Assert.notNull(annotationClazz, "Annotation must not be null");
		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());			
			for (Method method : methods) {				
				if (method.getAnnotation(annotationClazz) != null) 					
					result.add(method.getName());					
			}
			searchType = searchType.getSuperclass();
		}
		return result;
	}	
	
	public static ActionMethod getActionMethod(Object target, String action, String serviceActionParam, boolean serviceActionParamType) throws JsonParseException, JsonMappingException, IOException {
		Class<?> clazz = target.getClass();
		ActionMethodImpl actionMethod = new ActionMethodImpl();
		actionMethod.setTarget(target);
		if (!StringUtils.isEmpty(serviceActionParam)) {
			ObjectMapper mapper = new ObjectMapper();
			if (serviceActionParamType) {
				mapper.enableDefaultTyping(DefaultTyping.JAVA_LANG_OBJECT);
			}
			Map<String, Object> actionParamsMap = mapper.readValue(
				serviceActionParam, 
				mapper.getTypeFactory().constructMapType(LinkedHashMap.class, String.class, Object.class)
			);
			actionMethod.setArgs(new Object[actionParamsMap.size()]);
			actionMethod.setMethod(ReflectionUtils.findMethod(clazz, action, actionParamsMap, actionMethod.getArgs()));
		} else {
			actionMethod.setArgs(new Object[0]);
			actionMethod.setMethod(ReflectionUtils.findMethod(clazz, action));			
		}
		return actionMethod;			
	}	
	
	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T getAnnotation(Annotation[] annotations, Class<T> annotationType) {
		T ann = null;
		for (int i = 0; i < annotations.length; i++) {
			if (annotationType.isInstance(annotations[i])) {
				return (T) annotations[i];
			}
			ann = annotations[i].annotationType().getAnnotation(annotationType);
			if (ann != null) {
				break;
			}
		}
		return ann;
	}	
	
}
