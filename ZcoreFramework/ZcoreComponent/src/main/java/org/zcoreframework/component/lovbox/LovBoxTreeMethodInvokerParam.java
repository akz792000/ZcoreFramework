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
import org.zcoreframework.component.method.MethodInvokerParam;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=LovBoxTreeMethodInvokerParam.class, clazz=LovBoxTreeMethodInvoker.class)
public @interface LovBoxTreeMethodInvokerParam {
	
	MethodInvokerParam methodInvoker();
		
}
