/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.core;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.zcoreframework.validation.annotation.AnnotationHandlerType;
import org.zcoreframework.validation.handler.Handler;
import org.zcoreframework.validation.model.Model;

public abstract class AbstractBeanValidator implements Validator, ValidatorHandler {
	
	private static String PKG_PATH = "org.zcoreframework.validation.annotation";
	
	private HandlerClassLoader handlerClassLoader = new HandlerClassLoader();
	
	private List<Class<?>> registeredAnnotations = new ArrayList<>();
	
	public List<Class<?>> getRegisteredAnnotations() {
		return registeredAnnotations;
	}
	
	public void setRegisteredAnnotations(List<Class<?>> registeredAnnotations) {
		this.registeredAnnotations = registeredAnnotations;
	}
	
	public void addAnnotation(Class<?> clazz) {
		registeredAnnotations.add(clazz);
	}
	
	public void register() throws IOException, ClassNotFoundException {
	    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	    MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
	    String packageSearchPath = 
	    		ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + 
	    		ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(PKG_PATH)) + 
	    		"/*.class";
	    Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
	    for (Resource resource : resources) {
	        if (resource.isReadable()) {
	            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
	            if (isCandidate(metadataReader)) {
	            	registeredAnnotations.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
	            }
	        }
	    }
	}

	private boolean isCandidate(MetadataReader metadataReader) throws ClassNotFoundException {
	    try {
	        Class<?> clazz = Class.forName(metadataReader.getClassMetadata().getClassName());
	        if (clazz.getAnnotation(AnnotationHandlerType.class) != null) {
	            return true;
	        }
	    }
	    catch(Throwable e){
	    }
	    return false;
	}

    public final boolean supports(final Class<?> clazz) {
        return true;
    }
    
    protected List<Field> getDeclaredFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            CollectionUtils.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
       
    @Override
    public HandlerFieldLoader getHandler(Class<?> clazz) {
    	// get default handler field loader
    	HandlerFieldLoader handlerFieldLoader = handlerClassLoader.get(clazz);
    	if (handlerFieldLoader != null) {
    		return handlerFieldLoader;
    	}		   	
		synchronized (this) {
			/*
			 *  regsiter and for thread safe we must check it again
			 *  if you don't want to synchronize whole method 
			 */ 			
			handlerFieldLoader = handlerClassLoader.get(clazz);
	    	if (handlerFieldLoader != null) {
	    		return handlerFieldLoader;
	    	}
	    	// get handlers of field	    	
	    	for (Field field : getDeclaredFields(clazz)) {  	    		    	
		    	List<Handler<? extends Model>> handlers = new ArrayList<>();
		    	for (Annotation annotation : field.getAnnotations()) {
		    		if (registeredAnnotations.contains(annotation.annotationType())) {
						AnnotationHandlerType annotationHandlerType = (AnnotationHandlerType) annotation.annotationType().getAnnotation(AnnotationHandlerType.class);
						// create new instance of handler
						try {
							Constructor<?> constructor = annotationHandlerType.value().getDeclaredConstructor(Annotation.class);
							@SuppressWarnings("unchecked")
							Handler<? extends Model> handler = (Handler<? extends Model>) constructor.newInstance(annotation);
							handlers.add(handler);
						} catch (InstantiationException
								| IllegalAccessException | NoSuchMethodException | SecurityException |
								IllegalArgumentException | InvocationTargetException ex) {
							ReflectionUtils.handleReflectionException(ex);
						}
		    		}
		    	}		    	
		    	// set handlerFieldLoader
		    	if (handlers.size() > 0) {
		    		if (handlerFieldLoader == null) {
		    			handlerFieldLoader = new HandlerFieldLoader();
		    		}
		    		handlerFieldLoader.put(field, handlers);
		    	}	    	    	
	    	}	    	
	    	// set handlerClassLoader
	    	if (handlerFieldLoader != null) {
	    		handlerClassLoader.put(clazz, handlerFieldLoader);
	    		return handlerFieldLoader;
	    	}
	    	// else
	    	return null;
		}
    }
    
    protected void validate(ValidateBeanWrapper beanWrapper, HandlerFieldLoader handlerFieldLoader, final Errors errors) {
		for (Field field : handlerFieldLoader.getHandlers().keySet()) {			
			for (Handler<? extends Model> handler : handlerFieldLoader.getHandlers().get(field)) {
				if (!handler.validate(beanWrapper, field)) {
					Model model = handler.getModel();
					if (model.isRejectable()) {
						BindingResult bindingResult = ((BindingResult) errors);
						String fieldName = field.getName();
						FieldError fieldError = new FieldError(
								fieldName, 
								errors.getNestedPath() + fieldName, 
								null,
								false,
								bindingResult.resolveMessageCodes(model.getErrorCode(), fieldName), 
								model.getArgs(), 
								model.getErrorCode()
						);
						bindingResult.addError(fieldError);
					}
					break;
				}  						
			}
		}		
    }    

}