/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.induction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.ReflectionUtils;
import org.zcoreframework.base.util.ApplicationContextUtils;

public class BeanInductionHandlerImpl implements InvocationHandler {

	private String name;

	public BeanInductionHandlerImpl(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	protected <T> T getTargetObject(Object proxy) throws Exception {
		while ((AopUtils.isJdkDynamicProxy(proxy))) {
			return (T) getTargetObject(((Advised) proxy).getTargetSource().getTarget());
		}
		return (T) proxy; // expected to be cglib proxy then, which is simply a
							// specialized class
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		try {
			Object object = ApplicationContextUtils.getBean(name);
			Class<?> clazz = object.getClass();
			Method methodClass = ReflectionUtils.findMethod(clazz, method.getName(), method.getParameterTypes());
			return ReflectionUtils.invokeMethod(methodClass, object, params);
		} catch (UndeclaredThrowableException exp) {
			throw exp.getCause();
		}
	}
}
