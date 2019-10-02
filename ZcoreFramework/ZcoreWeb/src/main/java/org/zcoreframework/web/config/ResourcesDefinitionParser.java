/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.config;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.w3c.dom.Element;
import org.zcoreframework.base.config.MessageSourceDefinitionParser;
import org.zcoreframework.web.servlet.resource.ResourceRequestHandler;

class ResourcesDefinitionParser implements BeanDefinitionParser {

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		Object source = parserContext.extractSource(element);

		String resourceHandlerName = registerResourceHandler(parserContext, element, source);
		if (resourceHandlerName == null) {
			return null;
		}

		Map<String, String> urlMap = new ManagedMap<String, String>();
		String resourceRequestPath = element.getAttribute("mapping");
		if (!StringUtils.hasText(resourceRequestPath)) {
			parserContext.getReaderContext().error("The 'mapping' attribute is required.", parserContext.extractSource(element));
			return null;
		}
		urlMap.put(resourceRequestPath, resourceHandlerName);

		RootBeanDefinition handlerMappingDef = new RootBeanDefinition(SimpleUrlHandlerMapping.class);
		handlerMappingDef.setSource(source);
		handlerMappingDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		handlerMappingDef.getPropertyValues().add("urlMap", urlMap);

		String order = element.getAttribute("order");
		// use a default of near-lowest precedence, still allowing for even lower precedence in other mappings
		handlerMappingDef.getPropertyValues().add("order", StringUtils.hasText(order) ? order : Ordered.LOWEST_PRECEDENCE - 1);

		String beanName = parserContext.getReaderContext().generateBeanName(handlerMappingDef);
		parserContext.getRegistry().registerBeanDefinition(beanName, handlerMappingDef);
		parserContext.registerComponent(new BeanComponentDefinition(handlerMappingDef, beanName));

		// Ensure BeanNameUrlHandlerMapping (SPR-8289) and default HandlerAdapters are not "turned off"
		// Register HttpRequestHandlerAdapter
		MvcNamespaceUtils.registerDefaultComponents(parserContext, source);

		return null;
	}

	private String registerResourceHandler(ParserContext parserContext, Element element, Object source) {
		String locationAttr = element.getAttribute("location");
		if (!StringUtils.hasText(locationAttr)) {
			parserContext.getReaderContext().error("The 'location' attribute is required.", parserContext.extractSource(element));
			return null;
		}

		ManagedList<String> locations = new ManagedList<String>();
		locations.addAll(Arrays.asList(StringUtils.commaDelimitedListToStringArray(locationAttr)));

		RootBeanDefinition resourceHandlerDef = new RootBeanDefinition(ResourceRequestHandler.class);
		resourceHandlerDef.setSource(source);
		resourceHandlerDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		resourceHandlerDef.getPropertyValues().add("locations", locations);

		String cacheSeconds = element.getAttribute("cache-period");
		if (StringUtils.hasText(cacheSeconds)) {
			resourceHandlerDef.getPropertyValues().add("cacheSeconds", cacheSeconds);
		}
		
		Boolean parse = Boolean.valueOf(element.getAttribute("parse"));
		if (parse) {
			resourceHandlerDef.getPropertyValues().add("messageSource", new RuntimeBeanReference(MessageSourceDefinitionParser.MESSAGE_SOURCE));
		}

		String beanName = parserContext.getReaderContext().generateBeanName(resourceHandlerDef);
		parserContext.getRegistry().registerBeanDefinition(beanName, resourceHandlerDef);
		parserContext.registerComponent(new BeanComponentDefinition(resourceHandlerDef, beanName));
		return beanName;
	}

}