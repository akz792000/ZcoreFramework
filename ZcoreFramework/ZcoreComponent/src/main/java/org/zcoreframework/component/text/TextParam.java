/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.text;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=TextParam.class, clazz=Text.class)
public @interface TextParam {
	
	public String item() default "";	

}
