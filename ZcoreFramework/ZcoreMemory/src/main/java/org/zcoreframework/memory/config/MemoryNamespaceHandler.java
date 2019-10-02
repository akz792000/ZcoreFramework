/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.memory.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MemoryNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {	
		registerBeanDefinitionParser("context", new ContextDefinitionParser());	
	}

}
