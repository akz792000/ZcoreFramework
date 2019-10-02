/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.method;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.component.method.MethodInvoker.BeanType;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation = MethodInvokerParam.class, clazz = MethodInvoker.class)
public @interface MethodInvokerParam {

	BeanType beanType() default BeanType.APPLICATION;

	String bean();

	String method();
	
}