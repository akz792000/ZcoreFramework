/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ComponentNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("validation", new ValidationConfigDefinitionParser());
		registerBeanDefinitionParser("mapping", new MappingConfigDefinitionParser());
	}

}
