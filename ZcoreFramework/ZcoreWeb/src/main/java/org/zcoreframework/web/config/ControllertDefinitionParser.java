/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.w3c.dom.Element;
import org.zcoreframework.base.config.DataAccessDefinitionParser;
import org.zcoreframework.validation.config.AnnotationConfigDefinitionParser;
import org.zcoreframework.web.service.controller.ExceptionController;
import org.zcoreframework.web.service.controller.ServiceController;

public class ControllertDefinitionParser implements BeanDefinitionParser {

	private static final String PREFIX = "org.zcoreframework.web.";

	private static final String POSTFIX = ".controller";

	public static final String EXCEPTION_CONTROLLER = PREFIX + "exception" + POSTFIX;

	public static final String SERVICE_CONTROLLER = PREFIX + "service" + POSTFIX;

	/*
	 * There is no default resolver implementation used for Spring DispatcherServlets, 
	 * as an application might choose to parse its multipart requests itself. 
	 * To define an implementation, create a bean with the id "multipartResolver" in a 
	 * DispatcherServlet's application context. Such a resolver gets 
	 * applied to all requests handled by that DispatcherServlet. 
	 */
	public static final String MULTIPART_RESOLVER = "multipartResolver";

	private final Log logger = LogFactory.getLog(getClass());

	/*
	 * for see additional informtaion see
	 * org.springframework.web.servlet.config.
	 * AnnotationDrivenBeanDefinitionParser
	 */
	private RuntimeBeanReference getValidator(Element element, Object source, ParserContext parserContext) {
		return new RuntimeBeanReference(AnnotationConfigDefinitionParser.NAME);
	}
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		Object source = parserContext.extractSource(element);

		// exception controller
		if (!parserContext.getRegistry().containsBeanDefinition(EXCEPTION_CONTROLLER)) {
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(ExceptionController.class);
			rootBeanDefinition.setSource(source);
			rootBeanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			parserContext.getRegistry().registerBeanDefinition(EXCEPTION_CONTROLLER, rootBeanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(rootBeanDefinition, EXCEPTION_CONTROLLER));
			logger.info("register " + EXCEPTION_CONTROLLER + " bean definition.");
		}

		// service controller
		if (!parserContext.getRegistry().containsBeanDefinition(SERVICE_CONTROLLER)) {
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(ServiceController.class);
			rootBeanDefinition.setSource(source);
			rootBeanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			rootBeanDefinition.getPropertyValues().add("validator", getValidator(element, source, parserContext));
			rootBeanDefinition.getPropertyValues().add("methodTemplateExecutor", new RuntimeBeanReference(DataAccessDefinitionParser.METHOD_TEMPLATE_EXECUTOR));
			parserContext.getRegistry().registerBeanDefinition(SERVICE_CONTROLLER, rootBeanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(rootBeanDefinition, SERVICE_CONTROLLER));
			logger.info("register " + SERVICE_CONTROLLER + " bean definition.");
		}

		// multipart resolver
		if (element.getAttribute("multipart-resolver").equals("true")) {
			if (!parserContext.getRegistry().containsBeanDefinition(MULTIPART_RESOLVER)) {
				RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(CommonsMultipartResolver.class);
				rootBeanDefinition.setSource(source);
				rootBeanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
				rootBeanDefinition.getPropertyValues().add("defaultEncoding", "utf-8");
				parserContext.getRegistry().registerBeanDefinition(MULTIPART_RESOLVER, rootBeanDefinition);
				parserContext.registerComponent(new BeanComponentDefinition(rootBeanDefinition, MULTIPART_RESOLVER));
				logger.info("register " + MULTIPART_RESOLVER + " bean definition.");
			}
		}

		return null;
	}

}
