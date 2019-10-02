package org.zcoreframework.base.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.base.gateway.business.impl.GatewayBusinessImpl;

import java.util.Properties;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 *         Date 10/30/2017.
 */
public class GatewayDefinitionParser implements BeanDefinitionParser {

    private static final String PREFIX = "org.zcoreframework.base.";

    public static final String GATEWAY_BUSINESS = PREFIX + "gatewayBusiness";

    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        // get source
        Object source = parserContext.extractSource(element);

        // get property value
        Properties properties = PropertiesFactoryBean.getProperties();

        // method template executor
        if (!parserContext.getRegistry().containsBeanDefinition(GATEWAY_BUSINESS)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(GatewayBusinessImpl.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            // register
            parserContext.getRegistry().registerBeanDefinition(GATEWAY_BUSINESS, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, GATEWAY_BUSINESS));

            // log
            logger.info("register " + GATEWAY_BUSINESS + " bean definition.");
        }

        return null;
    }
}

