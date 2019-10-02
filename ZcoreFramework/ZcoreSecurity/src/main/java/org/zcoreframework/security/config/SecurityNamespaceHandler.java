/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SecurityNamespaceHandler extends NamespaceHandlerSupport {
	
	public static final String SECURITY_CONTEXT = "springSecurityFilterChain";

	@Override
	public void init() {
		registerBeanDefinitionParser("none", new NoneDefinitionParser());
		registerBeanDefinitionParser("client", new ClientDefinitionParser());
	}

}
