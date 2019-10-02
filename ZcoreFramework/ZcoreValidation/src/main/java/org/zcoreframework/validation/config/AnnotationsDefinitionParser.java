/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/*
 * use from AuthenticationManagerBeanDefinitionParser
 */

public class AnnotationsDefinitionParser implements BeanDefinitionParser {
	
	private static final String CLASS = "class";
	
	public static final String REGISTERED_ANNOTATIONS = "registeredAnnotations";
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		BeanDefinition beanDefinition = parserContext.getReaderContext().getRegistry().getBeanDefinition(AnnotationConfigDefinitionParser.NAME);
		logger.info("get annotation bean definition.");
		// get value of REGISTERED_ANNOTATIONS
		List<Class<?>> registeredAnnotations = new ArrayList<>();
        PropertyValue pv = beanDefinition.getPropertyValues().getPropertyValue(REGISTERED_ANNOTATIONS);
        if (pv != null) {
        	registeredAnnotations = (List<Class<?>>) pv.getValue();
        	logger.info("get value of " + REGISTERED_ANNOTATIONS);
        }		
		// add annotations
		NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
            	Element annotation = (Element)node;
            	String className = annotation.getAttribute(CLASS);
            	if (StringUtils.hasText(className)) {
            		try {
            			registeredAnnotations.add(ClassUtils.forName(className, ClassUtils.getDefaultClassLoader()));
            			logger.info("add class " + className + " successfully.");
					} catch (ClassNotFoundException | LinkageError e) {
						parserContext.getReaderContext().error(className + " not exist in class path", this);
					}
            	}
            	
            }
        }    
        // set property value
        if (pv == null) {
        	beanDefinition.getPropertyValues().add(REGISTERED_ANNOTATIONS, registeredAnnotations);
        	logger.info("set property value of " + REGISTERED_ANNOTATIONS);
        }
        return null;
	}
	
}
