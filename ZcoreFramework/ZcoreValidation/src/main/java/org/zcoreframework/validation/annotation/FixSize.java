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

import org.zcoreframework.validation.handler.FixSizeHandler;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@AnnotationHandlerType(FixSizeHandler.class)
public @interface FixSize {
	
	String errorCode() default "fix.size";
	
	String applyIf() default "";
      
    int value() default 0;

}
