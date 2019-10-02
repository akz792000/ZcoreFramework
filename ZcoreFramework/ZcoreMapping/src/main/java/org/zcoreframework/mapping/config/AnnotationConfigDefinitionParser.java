/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.mapping.core.BeanMapperImpl;

/*
 * use from AuthenticationManagerBeanDefinitionParser
 */

public class AnnotationConfigDefinitionParser implements BeanDefinitionParser {
	
	public static final String NAME = "beanMapper";
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		if (parserContext.getRegistry().containsBeanDefinition(NAME)) {
			parserContext.getReaderContext().error(NAME + " registered ...", parserContext.extractSource(element));
		}
        BeanDefinitionBuilder providerManagerBldr = BeanDefinitionBuilder.rootBeanDefinition(BeanMapperImpl.class);
        providerManagerBldr.setInitMethodName("register");
        parserContext.registerBeanComponent(new BeanComponentDefinition(providerManagerBldr.getBeanDefinition(), NAME));
        logger.info("register " + NAME + " bean definition.");
        return null;
	}
	
}
