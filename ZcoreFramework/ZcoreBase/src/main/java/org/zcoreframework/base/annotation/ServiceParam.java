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

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceParam {
	
	public enum Type { GET, POST, BOTH };

	public String key() default "";
	
	public boolean value() default true;
	
	public boolean data() default true;
	
	public int order() default 0;
	
	public Type type() default Type.BOTH;
	
}