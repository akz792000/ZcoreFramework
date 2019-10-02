/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.lovbox;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.component.grid.GridColumnParam;
import org.zcoreframework.component.method.MethodInvokerParam;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=LovBoxGridMethodInvokerParam.class, clazz=LovBoxGridMethodInvoker.class)
public @interface LovBoxGridMethodInvokerParam {
	
	MethodInvokerParam methodInvoker();
	
	GridColumnParam[] columns() default {};
	
}
