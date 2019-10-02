package org.zcoreframework.base.induction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.zcoreframework.base.induction.Induct.InductionType;

public class FactoryInductionHandler {

	@SuppressWarnings("unchecked")
	public static <T> T getInductionHandler(InductionType inductionType, Class<T> service) {
		// service context annotation & interfaces
		InductInfo annotation = service.getAnnotation(InductInfo.class);
		Assert.notNull(annotation, "service context annotation can not be null");
		Class<?>[] interfaces = { service };
		InvocationHandler invocationHandler;

		// create invocation handler
		switch (inductionType) {
		case REST:
			invocationHandler = new RestInductionHandlerImpl(annotation.context(), annotation.name());
			break;
		default:
			invocationHandler = new BeanInductionHandlerImpl(annotation.name());
			break;
		}

		// return
		return (T) Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), interfaces, invocationHandler);
	}

}
