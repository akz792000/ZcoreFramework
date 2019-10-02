/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.security.config;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.security.cas.rest.BranchAuthorizationClientFilterImpl;
import org.zcoreframework.security.cas.rest.CasConfiguration;
import org.zcoreframework.security.core.SessionRegistryImpl;
import org.zcoreframework.security.web.authorization.RestAuthorizationClientFilterImpl;
import org.zcoreframework.security.web.authorization.SoapAuthorizationClientFilterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ClientDefinitionParser implements BeanDefinitionParser {

    private final Log logger = LogFactory.getLog(ClientDefinitionParser.class);

    private static final String PREFIX = "org.zcoreframework.security.";

    public static final String CAS_CONFIGURATION = PREFIX + "casConfiguration";

    public static final String SESSION_REGISTRY = PREFIX + "sessionRegistry";

    public static final String REST_AUTHORIZATION_CLIENT_FILTER = PREFIX + "restAuthorizationClientFilter";

    public static final String SOAP_AUTHORIZATION_CLIENT_FILTER = PREFIX + "soapAuthorizationClientFilter";

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        // get source
        Object source = parserContext.extractSource(element);

        // get property value
        Properties properties = PropertiesFactoryBean.getProperties();

        // Cas Configuration
        if (!parserContext.getRegistry().containsBeanDefinition(CAS_CONFIGURATION)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(CasConfiguration.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            String authenticationUrl = "PROTOCOL://SERVER:PORT/NAME/zcore-ticket";
            authenticationUrl = authenticationUrl
                    .replace("PROTOCOL", (String) properties.get("zcore.security.service.protocol"))
                    .replace("SERVER", (String) properties.get("zcore.security.service.server"))
                    .replace("PORT", (String) properties.get("zcore.security.service.port"))
                    .replace("NAME", (String) properties.get("zcore.security.service.name"));

            // constructor argument
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, authenticationUrl);
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

            // register
            parserContext.getRegistry().registerBeanDefinition(CAS_CONFIGURATION, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, CAS_CONFIGURATION));

            // log
            logger.info("register " + CAS_CONFIGURATION + " bean definition.");
        }

        // Session Registry
        if (!parserContext.getRegistry().containsBeanDefinition(SESSION_REGISTRY)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(SessionRegistryImpl.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            // register
            parserContext.getRegistry().registerBeanDefinition(SESSION_REGISTRY, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, SESSION_REGISTRY));

            // log
            logger.info("register " + SESSION_REGISTRY + " bean definition.");
        }

        // prepare security processes urls ----------------------------------------------------------------------------
        final List<String> filterProcessesUrls = new ArrayList<>();
        final List<String> deterrentProcessesUrls = new ArrayList<>();
        final List<String> serviceProcessesUrls = new ArrayList<>();

        filterProcessesUrls.add("/view/**");
        filterProcessesUrls.add("/resources/**");
        addSecurityProcessesUrls(element, filterProcessesUrls, deterrentProcessesUrls, serviceProcessesUrls);
        // ------------------------------------------------------------------------------------------------------------

        // Rest Authorization Client Filter
        if (!parserContext.getRegistry().containsBeanDefinition(REST_AUTHORIZATION_CLIENT_FILTER)) {

            // bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RestAuthorizationClientFilterImpl.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            // constructor argument
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, filterProcessesUrls);
            constructorArgumentValues.addIndexedArgumentValue(1, deterrentProcessesUrls);
            constructorArgumentValues.addIndexedArgumentValue(2, serviceProcessesUrls);
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

            // set property value
            beanDefinition.getPropertyValues().add("casConfiguration", new RuntimeBeanReference(CAS_CONFIGURATION));
            beanDefinition.getPropertyValues().add("sessionRegistry", new RuntimeBeanReference(SESSION_REGISTRY));

            // register
            parserContext.getRegistry().registerBeanDefinition(REST_AUTHORIZATION_CLIENT_FILTER, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, REST_AUTHORIZATION_CLIENT_FILTER));
            logger.info("register " + REST_AUTHORIZATION_CLIENT_FILTER + " bean definition.");
        }

        // Soap Authorization Filter
        if (!parserContext.getRegistry().containsBeanDefinition(SOAP_AUTHORIZATION_CLIENT_FILTER)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(SoapAuthorizationClientFilterImpl.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            // constructor argument
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, filterProcessesUrls);
            constructorArgumentValues.addIndexedArgumentValue(1, deterrentProcessesUrls);
            constructorArgumentValues.addIndexedArgumentValue(2, serviceProcessesUrls);
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

            // set property value
            beanDefinition.getPropertyValues().add("casConfiguration", new RuntimeBeanReference(ClientDefinitionParser.CAS_CONFIGURATION));
            beanDefinition.getPropertyValues().add("sessionRegistry", new RuntimeBeanReference(ClientDefinitionParser.SESSION_REGISTRY));

            // register
            parserContext.getRegistry().registerBeanDefinition(SOAP_AUTHORIZATION_CLIENT_FILTER, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, SOAP_AUTHORIZATION_CLIENT_FILTER));

            // log
            logger.info("register " + SOAP_AUTHORIZATION_CLIENT_FILTER + " bean definition.");
        }

        // properties factory bean
        if (!parserContext.getRegistry().containsBeanDefinition(SecurityNamespaceHandler.SECURITY_CONTEXT)) {

            // bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(BranchAuthorizationClientFilterImpl.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            // set property value
            beanDefinition.getPropertyValues().add("authorizationClientFilter", new RuntimeBeanReference(REST_AUTHORIZATION_CLIENT_FILTER));

            // register
            parserContext.getRegistry().registerBeanDefinition(SecurityNamespaceHandler.SECURITY_CONTEXT, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, SecurityNamespaceHandler.SECURITY_CONTEXT));
            logger.info("register " + SecurityNamespaceHandler.SECURITY_CONTEXT + " bean definition.");
        }

        return null;
    }

    private void addSecurityProcessesUrls(Element element, List<String> filterProcessesUrls, List<String> deterrentProcessesUrls, List<String> serviceProcessesUrls) {
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                Element child = (Element) node;
                switch (child.getNodeName()) {
                    case "security:filter":
                        prepareItems(child, filterProcessesUrls);
                        break;
                    case "security:deterrent":
                        prepareItems(child, deterrentProcessesUrls);
                        break;
                    case "security:service":
                        prepareItems(child, serviceProcessesUrls);
                        break;
                    default:
                        break;

                }
            }
        }
    }

    private void prepareItems(Element element, List<String> list) {
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node instanceof Element) {
                NodeList items = node.getChildNodes();
                for (int j = 0; j < items.getLength(); j++) {
                    Node value = items.item(j);
                    if (value instanceof Element) {
                        list.add(value.getTextContent());
                    }
                }
            }
        }
    }
}