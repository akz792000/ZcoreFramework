/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.base.induction.ServiceUtils;
import org.zcoreframework.base.util.BeanDefinitionUtils;

public class ServiceDefinitionParser implements BeanDefinitionParser {

	private static final String PREFIX = "org.zcoreframework.base.";
	
	public static final String SERVICE_UTILS = PREFIX + "serviceUtils";

	private final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// get source
		Object source = parserContext.extractSource(element);
		
		// logger utils
		Map<String, Object> properties = new HashMap<>();
		properties.put("properties", new RuntimeBeanReference(ContextDefinitionParser.PROPERTIES_FACTORY_BEAN));
		BeanDefinitionUtils.registerComponent(parserContext, source, SERVICE_UTILS, ServiceUtils.class, properties);
		logger.info("register " + SERVICE_UTILS + " bean definition.");
		
        return null;
        
	}

}
