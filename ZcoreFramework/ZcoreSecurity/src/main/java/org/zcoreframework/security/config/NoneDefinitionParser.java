/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.security.cas.rest.NoneSecurityClientFilter;

public class NoneDefinitionParser implements BeanDefinitionParser {

	private final Log logger = LogFactory.getLog(NoneDefinitionParser.class);

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {

		// get source
		Object source = parserContext.extractSource(element);

		// properties factory bean
		if (!parserContext.getRegistry().containsBeanDefinition(SecurityNamespaceHandler.SECURITY_CONTEXT)) {

			// bean definition
			RootBeanDefinition beanDefinition = new RootBeanDefinition(NoneSecurityClientFilter.class);
			beanDefinition.setSource(source);
			beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

			// register
			parserContext.getRegistry().registerBeanDefinition(SecurityNamespaceHandler.SECURITY_CONTEXT, beanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, SecurityNamespaceHandler.SECURITY_CONTEXT));
			logger.info("register " + SecurityNamespaceHandler.SECURITY_CONTEXT + " bean definition.");

		}

		return null;

	}

}