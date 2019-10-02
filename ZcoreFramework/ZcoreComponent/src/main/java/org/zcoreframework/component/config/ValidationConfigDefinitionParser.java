/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;
import org.zcoreframework.component.validation.ValueExist;
import org.zcoreframework.validation.config.AnnotationConfigDefinitionParser;
import org.zcoreframework.validation.config.AnnotationsDefinitionParser;

/*
 * use from AuthenticationManagerBeanDefinitionParser
 */

public class ValidationConfigDefinitionParser implements BeanDefinitionParser {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		BeanDefinition beanDefinition = parserContext.getReaderContext().getRegistry().getBeanDefinition(AnnotationConfigDefinitionParser.NAME);
		logger.info("get annotation bean definition.");
		// get value of REGISTERED_ANNOTATIONS
		List<Class<?>> registeredAnnotations = new ArrayList<>();
        PropertyValue pv = beanDefinition.getPropertyValues().getPropertyValue(AnnotationsDefinitionParser.REGISTERED_ANNOTATIONS);
        if (pv != null) {
        	registeredAnnotations = (List<Class<?>>) pv.getValue();
        	logger.info("get value of " + AnnotationsDefinitionParser.REGISTERED_ANNOTATIONS); 
        }
        // add ValueExist
		String canonicalName = ValueExist.class.getCanonicalName();
    	try {
			registeredAnnotations.add(ClassUtils.forName(canonicalName, ClassUtils.getDefaultClassLoader()));
			logger.info("add class " + canonicalName + " successfully.");
		} catch (ClassNotFoundException | LinkageError e) {
			parserContext.getReaderContext().error(canonicalName + " not exist in class path", this);
		}
        // set property value
        if (pv == null) {
        	beanDefinition.getPropertyValues().add(AnnotationsDefinitionParser.REGISTERED_ANNOTATIONS, registeredAnnotations);
        	logger.info("set property value of " + AnnotationsDefinitionParser.REGISTERED_ANNOTATIONS);
        }
        return null;
	}
	
}
