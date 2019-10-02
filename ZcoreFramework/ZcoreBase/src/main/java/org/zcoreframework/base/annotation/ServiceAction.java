/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.component.ResponseResult.ResponseType;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceAction {
		
	ResponseType response() default ResponseType.JSON;

	boolean validation() default true;	
	
	boolean methodTemplateExecutor() default true;
	
	Class<? extends Throwable>[] noRollbackFor() default {};
	
}