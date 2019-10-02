/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;




import org.zcoreframework.base.beans.factory.annotation.ComponentType;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=SimpleMapEntityTargetParam.class, clazz=SimpleMapEntityTarget.class)
public @interface SimpleMapEntityTargetParam {
		
	public Class<?> model();
	
	public Class<?> entity() default Object.class;
	
	public String key();
	
	public String join() default "";
	
	public String order();
			
}