package org.zcoreframework.component.menu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.component.method.MethodInvokerParam;

/**
 * Created by z.azadi on 7/11/2016.
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation = MenuMethodInvokerParam.class, clazz = MenuMethodInvoker.class)
public @interface MenuMethodInvokerParam {

    MethodInvokerParam methodInvoker();
}
