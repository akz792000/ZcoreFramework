/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.validation.handler.ExamineHandler;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@AnnotationHandlerType(ExamineHandler.class)
public @interface Examine {
	
	String errorCode() default "examine";
	
	String applyIf() default "";
	
	String value();
	
	Class<?> clazz() default ExamineHandler.DEFAULT.class;

}
