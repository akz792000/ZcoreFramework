/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.service.controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;
import org.zcoreframework.base.annotation.Service;
import org.zcoreframework.base.method.MethodTemplateExecutor;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.web.method.RequestMethodTemplateExecutor;

public abstract class AbstractServiceController implements RequestMethodTemplateExecutor {
	
	private Validator validator;

	private MethodTemplateExecutor methodTemplateExecutor;

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public MethodTemplateExecutor getMethodTemplateExecutor() {
		return methodTemplateExecutor;
	}

	public void setMethodTemplateExecutor(MethodTemplateExecutor methodTemplateExecutor) {
		this.methodTemplateExecutor = methodTemplateExecutor;
	}

	protected AbstractServiceImpl getBean(String name) throws IllegalArgumentException, SecurityException {
		Object object = ApplicationContextUtils.getApplicationContext().getBean(name);
		Assert.notNull(AnnotationUtils.findAnnotation(object.getClass(), Service.class), "Service can not invoke ...");
		return (AbstractServiceImpl) object;
	}

}