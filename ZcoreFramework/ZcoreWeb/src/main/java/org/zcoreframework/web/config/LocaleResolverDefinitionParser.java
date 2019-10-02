/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.w3c.dom.Element;
import org.zcoreframework.base.util.BeanDefinitionUtils;

public class LocaleResolverDefinitionParser implements BeanDefinitionParser {

	public static final String LOCALE_RESOLVER = "localeResolver";
	
	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// get source
		Object source = parserContext.extractSource(element);
		
		// register locale resolver 
		Map<String, Object> properties = new HashMap<>();
		properties.put("cookieName", element.getAttribute("cookieName"));
		properties.put("cookieMaxAge", element.getAttribute("cookieMaxAge"));
		properties.put("defaultLocale", element.getAttribute("defaultLocale"));
		properties.put("cookiePath", element.getAttribute("cookiePath"));
		properties.put("cookieSecure", element.getAttribute("cookieSecure"));
		BeanDefinitionUtils.registerComponent(parserContext, source, LOCALE_RESOLVER, CookieLocaleResolver.class, properties);
		logger.info("register " + LOCALE_RESOLVER + " bean definition.");

		return null;
	}

}
