/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.base.beans.factory.InitializeAnnotationBeanPostProcessor;
import org.zcoreframework.base.core.ApplicationServerSpecific;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.base.util.BeanDefinitionUtils;

public class ContextDefinitionParser implements BeanDefinitionParser {

	private static final String PREFIX = "org.zcoreframework.base.";

	public static final String PROPERTIES_FACTORY_BEAN = PREFIX + "propertiesFactoryBean";

	public static final String PROPERTY_PLACE_HOLDER_CONFIGURER = PREFIX + "propertyPlaceHolderConfigurer";

	public static final String BEAN_POST_PROCESSOR = PREFIX + "beanPostProcessor";

	public static final String APPLICATION_CONTEXT = PREFIX + "applicationContext";

	public static final String APPLICATION_SERVER_SPECIFIC = PREFIX + "applicationServerSpecific";

	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// get source
		Object source = parserContext.extractSource(element);

		Map<String, Object> properties;

		// properties factory bean
		if (!parserContext.getRegistry().containsBeanDefinition(PROPERTIES_FACTORY_BEAN)) {
			RootBeanDefinition beanDefinition = new RootBeanDefinition(PropertiesFactoryBean.class);
			beanDefinition.setSource(source);
			beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			beanDefinition.setFactoryMethodName("getPropertiesFactoryBean");

			// register
			parserContext.getRegistry().registerBeanDefinition(PROPERTIES_FACTORY_BEAN, beanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, PROPERTIES_FACTORY_BEAN));
			logger.info("register " + PROPERTIES_FACTORY_BEAN + " bean definition.");
		}

		// property place holder configurer
		properties = new HashMap<>();
		properties.put("properties", new RuntimeBeanReference(PROPERTIES_FACTORY_BEAN));
		BeanDefinitionUtils.registerComponent(parserContext, source, PROPERTY_PLACE_HOLDER_CONFIGURER, PropertyPlaceholderConfigurer.class, properties);
		logger.info("register " + PROPERTY_PLACE_HOLDER_CONFIGURER + " bean definition.");

		// bean post processor
		BeanDefinitionUtils.registerComponent(parserContext, source, BEAN_POST_PROCESSOR, InitializeAnnotationBeanPostProcessor.class, null);
		logger.info("register " + BEAN_POST_PROCESSOR + " bean definition.");

		// application context
		BeanDefinitionUtils.registerComponent(parserContext, source, APPLICATION_CONTEXT, ApplicationContextUtils.class, null);
		logger.info("register " + APPLICATION_CONTEXT + " bean definition.");

		// application server specific
		BeanDefinitionUtils.registerComponent(parserContext, source, APPLICATION_SERVER_SPECIFIC, ApplicationServerSpecific.class, null);
		logger.info("register " + APPLICATION_SERVER_SPECIFIC + " bean definition.");

		return null;
	}

}
