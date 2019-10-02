package org.zcoreframework.pos.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.pos.server.ISOServerImpl;
import org.zcoreframework.pos.service.impl.ISOServiceDispatcherImpl;
import org.zcoreframework.pos.service.impl.ISOServiceRegistryImpl;
import org.zcoreframework.pos.service.impl.ISOTransactionServiceImpl;

import java.util.Properties;

/**
 *
 */
public class ISOServerDefinitionParser implements BeanDefinitionParser {

    private static final String ISO_DISPATCHER = "org.zcoreframework.pos.service.ISOServiceDispatcher";

    private static final String ISO_SERVER = "org.zcoreframework.pos.server.ISOServerImpl";

    private static final String ISO_REGISTRY = "org.zcoreframework.pos.service.ISOServiceRegistry";

    private static final String ISO_TX_BIZ = "org.zcoreframework.pos.service.ISOTransactionService";

    private final Log logger = LogFactory.getLog(ISOServerDefinitionParser.class);

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        Object source = parserContext.extractSource(element);

        Properties properties = PropertiesFactoryBean.getProperties();

        if (!parserContext.getRegistry().containsBeanDefinition(ISO_TX_BIZ)) {
            RootBeanDefinition beanDefinition = new RootBeanDefinition(ISOTransactionServiceImpl.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            parserContext.getRegistry().registerBeanDefinition(ISO_TX_BIZ, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, ISO_TX_BIZ));

            logger.info("register " + ISO_TX_BIZ + " bean definition.");
        }

        if (!parserContext.getRegistry().containsBeanDefinition(ISO_REGISTRY)) {
            RootBeanDefinition beanDefinition = new RootBeanDefinition(ISOServiceRegistryImpl.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

//            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
//            constructorArgumentValues.addIndexedArgumentValue(0, getServiceBeanNames(element));
//            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

            parserContext.getRegistry().registerBeanDefinition(ISO_REGISTRY, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, ISO_REGISTRY));

            logger.info("register " + ISO_REGISTRY + " bean definition.");
        }

        if (!parserContext.getRegistry().containsBeanDefinition(ISO_DISPATCHER)) {
            RootBeanDefinition beanDefinition = new RootBeanDefinition(ISOServiceDispatcherImpl.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            beanDefinition.getPropertyValues().add("registry", new RuntimeBeanReference(ISO_REGISTRY));
            beanDefinition.getPropertyValues().add("txService", new RuntimeBeanReference(ISO_TX_BIZ));

            parserContext.getRegistry().registerBeanDefinition(ISO_DISPATCHER, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, ISO_DISPATCHER));

            logger.info("register " + ISO_DISPATCHER + " bean definition.");
        }

        if (!parserContext.getRegistry().containsBeanDefinition(ISO_SERVER)) {
            String name = (String) properties.get("zcore.pos.server.name");
            String port = (String) properties.get("zcore.pos.server.port");
            String minSessions = (String) properties.get("zcore.pos.server.minSessions");
            String maxSessions = (String) properties.get("zcore.pos.server.maxSessions");

            RootBeanDefinition beanDefinition = new RootBeanDefinition(ISOServerImpl.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, name == null ? "ISOServerImpl" : name);
            constructorArgumentValues.addIndexedArgumentValue(1, port == null ? 0 : Integer.valueOf(port));
            constructorArgumentValues.addIndexedArgumentValue(2, minSessions == null ? 1 : Integer.valueOf(minSessions));
            constructorArgumentValues.addIndexedArgumentValue(3, maxSessions == null ? 1 : Integer.valueOf(maxSessions));
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

            beanDefinition.getPropertyValues().add("dispatcher", new RuntimeBeanReference(ISO_DISPATCHER));

            parserContext.getRegistry().registerBeanDefinition(ISO_SERVER, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, ISO_SERVER));

            logger.info("register " + ISO_SERVER + " bean definition.");
        }

        return null;
    }
}
