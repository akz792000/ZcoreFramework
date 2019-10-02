/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.mapping.annotation.AnnotationHandlerType;
import org.zcoreframework.mapping.core.BeanMapper.MappingDirection;
import org.zcoreframework.mapping.mapper.ModelFieldBeanMapperImpl;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@AnnotationHandlerType(MapperHandler.class)
public @interface Mapper {
	
	MappingDirection mappingDirection() default MappingDirection.BIDIRECTIONAL; 
	
	Class<?> target() default ModelFieldBeanMapperImpl.class;
	
	String[] info() default {};

}