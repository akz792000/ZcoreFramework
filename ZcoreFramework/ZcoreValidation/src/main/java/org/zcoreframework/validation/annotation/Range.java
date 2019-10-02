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

import org.zcoreframework.validation.handler.RangeHandler;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@AnnotationHandlerType(RangeHandler.class)
public @interface Range {
	
	String errorCode() default "range";
	
	String applyIf() default "";
    
	double min() default 0;
    
    double max() default Double.MAX_VALUE;

}
