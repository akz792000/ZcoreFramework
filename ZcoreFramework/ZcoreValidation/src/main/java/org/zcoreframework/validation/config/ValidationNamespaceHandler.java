/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ValidationNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("annotation-config", new AnnotationConfigDefinitionParser());	
		registerBeanDefinitionParser("annotations", new AnnotationsDefinitionParser());	
	}

}
