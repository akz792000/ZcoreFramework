/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.component.method.MethodInvokerParam;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=ListValueMethodInvokerParam.class, clazz=ListValueMethodInvoker.class)
public @interface ListValueMethodInvokerParam {
	
	MethodInvokerParam methodInvoker();

}
