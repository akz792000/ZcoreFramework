/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.method;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.induction.FactoryInductionHandler;
import org.zcoreframework.base.induction.Induct.InductionType;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.base.util.ReflectionUtils;

public class MethodInvoker extends AbstractComponentImpl {

	private static final Log log = LogFactory.getLog(MethodInvoker.class);

	public static enum BeanType {
		APPLICATION, CREATE, CLASS, STATIC, INDUCT_BEAN, INDUCT_REST
	};

	private BeanType beanType = BeanType.APPLICATION;

	private String bean;

	private String method;

	private Object[] args;

	public BeanType getBeanType() {
		return beanType;
	}

	public void setBeanType(BeanType beanType) {
		this.beanType = beanType;
	}

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object... args) {
		this.args = args;
	}

	@SuppressWarnings("unchecked")
	public Object invoke() {
		// get bean
		Assert.isTrue(!StringUtils.isEmpty(bean), "Bean can not be null or empty string...");
		Object beanObj = null;
		Class<? extends Object> clazz = null;
		switch (beanType) {
		case INDUCT_REST:
		case INDUCT_BEAN:
			try {
				InductionType inductionType = beanType.equals(BeanType.INDUCT_REST) ? InductionType.REST : InductionType.BEAN;
				beanObj = FactoryInductionHandler.getInductionHandler(inductionType, Class.forName(bean));
				clazz = beanObj.getClass();
			} catch (ClassNotFoundException e) {
				ReflectionUtils.handleReflectionException(e);
			}
			break;
		case CREATE:
			try {
				beanObj = ApplicationContextUtils.createBean(Class.forName(bean));
				clazz = beanObj.getClass();
			} catch (ClassNotFoundException e) {
				ReflectionUtils.handleReflectionException(e);
			}
			break;
		case CLASS:
			try {
				beanObj = Class.forName(bean).newInstance();
				clazz = beanObj.getClass();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				ReflectionUtils.handleReflectionException(e);
			}
			break;
		case STATIC:
			try {
				beanObj = Class.forName(bean);
				clazz = (Class<? extends Object>) beanObj;
			} catch (ClassNotFoundException e) {
				ReflectionUtils.handleReflectionException(e);
			}
			break;
		default:
			beanObj = ApplicationContextUtils.getApplicationContext().getBean(bean);
			clazz = beanObj.getClass();
			break;
		}
		Assert.notNull(beanObj, "bean can not be null");
		// get method
		Assert.isTrue(!StringUtils.isEmpty(method), "Method can not be null or empty string...");
		try {
			Method methodObj = ReflectionUtils.findMethod(clazz, method, args);
			Assert.notNull(methodObj, "Method can not be null");
			// invoke method
			return ReflectionUtils.invokeMethod(methodObj, beanObj, args);
		} catch (IllegalArgumentException e) {
			log.debug("Illegal argument exception");
			e.printStackTrace();
			return null;
		}
	}

}
