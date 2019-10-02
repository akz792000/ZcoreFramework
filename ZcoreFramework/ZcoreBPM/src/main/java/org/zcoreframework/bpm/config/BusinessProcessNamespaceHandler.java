/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.bpm.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class BusinessProcessNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("context", new ContextDefinitionParser());
	}

}
