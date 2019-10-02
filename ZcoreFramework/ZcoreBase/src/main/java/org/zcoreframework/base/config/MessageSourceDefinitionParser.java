/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.base.context.WildcardReloadableResourceBundleMessageSource;
import org.zcoreframework.base.util.BeanDefinitionUtils;
import org.zcoreframework.base.util.MessageSourceUtils;

public class MessageSourceDefinitionParser implements BeanDefinitionParser {

	private static final String PREFIX = "org.zcoreframework.base.";

	// must have "messageSource" to inject properly with MessageSourceAware
	public static final String MESSAGE_SOURCE = "messageSource";
	
	public static final String MESSAGE_SOURCE_UTILS = PREFIX + "messageSourceUtils";

	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// get source
		Object source = parserContext.extractSource(element);

		// register message source
		Map<String, Object> properties = new HashMap<>();
		List<String> basenames = new ArrayList<>();
		basenames.add(element.getAttribute("location"));
		properties.put("basenames", basenames);
		properties.put("defaultEncoding", "UTF-8");
		BeanDefinitionUtils.registerComponent(parserContext, source, MESSAGE_SOURCE, WildcardReloadableResourceBundleMessageSource.class, properties);
		logger.info("register " + MESSAGE_SOURCE + " bean definition.");
		
		// register message source utils 		
		BeanDefinitionUtils.registerComponent(parserContext, source, MESSAGE_SOURCE_UTILS, MessageSourceUtils.class, null);
		logger.info("register " + MESSAGE_SOURCE_UTILS + " bean definition.");

		return null;
	}

}
