/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.report.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ReportNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("context", new ContextDefinitionParser());
	}

}
