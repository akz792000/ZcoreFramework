/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.memory.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.Assert;
import org.w3c.dom.Element;
import org.zcoreframework.memory.ClientJointMemoryImpl;
import org.zcoreframework.memory.ServerJointMemoryImpl;
import org.zcoreframework.memory.util.JointMemoryUtils;

public class ContextDefinitionParser implements BeanDefinitionParser {

	private final Log logger = LogFactory.getLog(ContextDefinitionParser.class);

	private static final String PREFIX = "org.zcoreframework.memory.";

	public static final String JOINT_MEMORY = PREFIX + "jointMemory";

	public static final String JOINT_MEMORY_UTILS = PREFIX + "jointMemoryUtils";

	private enum JointMemoryType { CLIENT, SERVER }

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {

		// get source
		Object source = parserContext.extractSource(element);

		// get joint memory type
		JointMemoryType jointMemoryType = JointMemoryType.valueOf(element.getAttribute("type").toUpperCase());

		// joint memmory
		if (!parserContext.getRegistry().containsBeanDefinition(JOINT_MEMORY)) {

			RootBeanDefinition beanDefinition = null;
			switch (jointMemoryType) {
			case CLIENT:
				beanDefinition = new RootBeanDefinition(ClientJointMemoryImpl.class);
				break;
			case SERVER:
				beanDefinition = new RootBeanDefinition(ServerJointMemoryImpl.class);
				break;
			default:
				Assert.isTrue(false, "Uknown joint memory type.");
				break;
			}

			// basic bean definition
			beanDefinition.setSource(source);
			beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

			// register
			parserContext.getRegistry().registerBeanDefinition(JOINT_MEMORY, beanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, JOINT_MEMORY));

			// log
			logger.info("register " + JOINT_MEMORY + " bean definition.");

		}

		if (!parserContext.getRegistry().containsBeanDefinition(JOINT_MEMORY_UTILS)) {

			// basic bean definition
			RootBeanDefinition beanDefinition = new RootBeanDefinition(JointMemoryUtils.class);
			beanDefinition.setSource(source);
			beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

			// set property value
			beanDefinition.getPropertyValues().add("jointMemory", new RuntimeBeanReference(JOINT_MEMORY));

			// register
			parserContext.getRegistry().registerBeanDefinition(JOINT_MEMORY_UTILS, beanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, JOINT_MEMORY_UTILS));

			// log
			logger.info("register " + JOINT_MEMORY_UTILS + " bean definition.");

		}

		return null;

	}

}