/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MappingNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("annotation-config", new AnnotationConfigDefinitionParser());
		registerBeanDefinitionParser("annotations", new AnnotationsDefinitionParser());	
	}

}
