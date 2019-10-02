/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.mapping.core.BeanMapper.MappingDirection;
import org.zcoreframework.mapping.handler.ClassMapperHandler;
import org.zcoreframework.mapping.mapper.ModelFieldBeanMapperImpl;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@AnnotationHandlerType(ClassMapperHandler.class)
public @interface ClassMapper {
	
	MappingDirection mappingDirection() default MappingDirection.BIDIRECTIONAL; 
	
	Class<?> source() default ModelFieldBeanMapperImpl.class;
	
	Class<?> target() default ModelFieldBeanMapperImpl.class;

}