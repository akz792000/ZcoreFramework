/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class WebNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("locale-resolver", new LocaleResolverDefinitionParser());
		registerBeanDefinitionParser("resources", new ResourcesDefinitionParser());
		registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenBeanDefinitionParser());
		registerBeanDefinitionParser("controller", new ControllertDefinitionParser());
        registerBeanDefinitionParser("soap", new SoapDefinitionParser());
	}

}
