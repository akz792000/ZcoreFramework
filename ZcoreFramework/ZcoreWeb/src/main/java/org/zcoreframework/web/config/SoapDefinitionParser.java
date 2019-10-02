/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.web.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.base.config.DataAccessDefinitionParser;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.security.config.ClientDefinitionParser;
import org.zcoreframework.web.service.soap.SoapController;

import java.util.Properties;

public class SoapDefinitionParser implements BeanDefinitionParser {

    private static final String PREFIX = "org.zcoreframework.web.";

    private static final String POSTFIX = ".controller";

    public static final String SOAP_CONTROLLER = PREFIX + "soap" + POSTFIX;

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        // get source
        Object source = parserContext.extractSource(element);

        // get property value
        Properties properties = PropertiesFactoryBean.getProperties();

        // service controller
        if (!parserContext.getRegistry().containsBeanDefinition(SOAP_CONTROLLER)) {
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(SoapController.class);
            rootBeanDefinition.setSource(source);
            rootBeanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            rootBeanDefinition.setInitMethodName("publish");
            rootBeanDefinition.getPropertyValues().add("port", properties.get("zcore.soap.service.port"));
            rootBeanDefinition.getPropertyValues().add("authorizationClientFilter", new RuntimeBeanReference(ClientDefinitionParser.SOAP_AUTHORIZATION_CLIENT_FILTER));
            rootBeanDefinition.getPropertyValues().add("methodTemplateExecutor", new RuntimeBeanReference(DataAccessDefinitionParser.METHOD_TEMPLATE_EXECUTOR));
            parserContext.getRegistry().registerBeanDefinition(SOAP_CONTROLLER, rootBeanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(rootBeanDefinition, SOAP_CONTROLLER));
            logger.info("register " + SOAP_CONTROLLER + " bean definition.");
        }

        return null;
    }
}
