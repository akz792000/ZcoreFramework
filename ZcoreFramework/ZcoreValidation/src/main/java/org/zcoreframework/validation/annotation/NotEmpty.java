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

import org.zcoreframework.validation.handler.NotEmptyHandler;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@AnnotationHandlerType(NotEmptyHandler.class)
public @interface NotEmpty {
	
	String errorCode() default "not.empty";
	
	String applyIf() default "";

}
