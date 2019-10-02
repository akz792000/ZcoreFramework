/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import java.util.Map;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.ParserContext;

public class BeanDefinitionUtils {
	
	public static void registerComponent(ParserContext parserContext, Object source, String name, Class<?> clazz, Map<String, Object> properties) {
		if (!parserContext.getRegistry().containsBeanDefinition(name)) {
			RootBeanDefinition beanDefinition = new RootBeanDefinition(clazz);
			beanDefinition.setSource(source);
			beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

			// set property values
			if (properties != null) {
				MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
				for (Map.Entry<String, Object> entry : properties.entrySet()) {
					mutablePropertyValues.add(entry.getKey(), entry.getValue());
				}
			}

			// register
			parserContext.getRegistry().registerBeanDefinition(name, beanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, name));
			
		}
	}

}
