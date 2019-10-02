/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.report.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.base.util.BeanDefinitionUtils;
import org.zcoreframework.report.util.ReportUtils;

public class ContextDefinitionParser implements BeanDefinitionParser {
	
	private static final String PREFIX = "org.zcoreframework.report.";
		
	public static final String REPORT_UTILS = PREFIX + "reportUtils";

	private final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// get source
		Object source = parserContext.extractSource(element);
		
		// logger utils
		Map<String, Object> properties = new HashMap<>();
		properties.put("properties", new RuntimeBeanReference(org.zcoreframework.base.config.ContextDefinitionParser.PROPERTIES_FACTORY_BEAN));
		BeanDefinitionUtils.registerComponent(parserContext, source, REPORT_UTILS, ReportUtils.class, properties);
		logger.info("register " + REPORT_UTILS + " bean definition.");
		
        return null;
        
	}
	
}
