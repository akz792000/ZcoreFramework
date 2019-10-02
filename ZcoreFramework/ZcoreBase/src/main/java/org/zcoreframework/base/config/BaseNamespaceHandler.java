/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class BaseNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("context", new ContextDefinitionParser());
		registerBeanDefinitionParser("message-source", new MessageSourceDefinitionParser());
		registerBeanDefinitionParser("data-access", new DataAccessDefinitionParser());
		registerBeanDefinitionParser("service", new ServiceDefinitionParser());
		registerBeanDefinitionParser("gateway", new GatewayDefinitionParser());
		registerBeanDefinitionParser("transaction-template", new TransactionTemplateDefinitionParser());
	}

}
