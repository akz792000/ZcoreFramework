/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.bpm.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.zcoreframework.base.util.ConstantsUtils.Schema;
import org.zcoreframework.bpm.core.BusinessProcessRegistry;
import org.zcoreframework.bpm.core.BusinessProcessServiceImpl;

import java.util.Arrays;
import java.util.List;

public class ContextDefinitionParser implements BeanDefinitionParser {

    public static final String BUSINESS_PROCESS_SERVICE = Schema.ZC + "_" + "businessProcess"; // static name
    private static final String PREFIX = "org.zcoreframework.camunda.";
    public static final String PROCESS_ENGINE_FACTORY_BEAN = PREFIX + "processEngineFactoryBean";
    public static final String REGISTRY = PREFIX + "registry";
    private final Log logger = LogFactory.getLog(getClass());

    private void registerProcessEngineService(RootBeanDefinition rootBeanDefinition, String name) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setFactoryBeanName(PROCESS_ENGINE_FACTORY_BEAN);
        beanDefinition.setFactoryMethodName("get" + name);
        rootBeanDefinition.getPropertyValues().add(Character.toLowerCase(name.charAt(0)) + name.substring(1), beanDefinition);
    }

    private void parseRegistry(Element element, ParserContext parserContext, Object source) {
        if (!parserContext.getRegistry().containsBeanDefinition(REGISTRY)) {

            // data source
            RuntimeBeanReference dataSource = new RuntimeBeanReference("org.zcoreframework.base.dataSource");

            // transaction manager
            RuntimeBeanReference transactionManager = new RuntimeBeanReference("org.zcoreframework.base.transactionManager");

            // process engine configuration
            RootBeanDefinition processEngineConfiguration = new RootBeanDefinition(SpringProcessEngineConfiguration.class);
            processEngineConfiguration.getPropertyValues().add("processEngineName", "engine");
            processEngineConfiguration.getPropertyValues().add("dataSource", dataSource);
            processEngineConfiguration.getPropertyValues().add("transactionManager", transactionManager);
            processEngineConfiguration.getPropertyValues().add("databaseSchemaUpdate", element.getAttribute("databaseSchemaUpdate"));
            processEngineConfiguration.getPropertyValues().add("databaseTablePrefix", element.getAttribute("databaseTablePrefix"));
            processEngineConfiguration.getPropertyValues().add("databaseType", "oracle");
            processEngineConfiguration.getPropertyValues().add("jobExecutorActivate", "true");
            processEngineConfiguration.getPropertyValues().add("deploymentResources", "classpath:META-INF/bpm/*.*mn");

            // dmn engine configuration
            DefaultDmnEngineConfiguration configuration = (DefaultDmnEngineConfiguration) DmnEngineConfiguration.createDefaultDmnEngineConfiguration();
            configuration.setDefaultInputExpressionExpressionLanguage("groovy");
            processEngineConfiguration.getPropertyValues().add("dmnEngineConfiguration", configuration);

            // process engine factory bean
            RootBeanDefinition processEngineFactoryBean = new RootBeanDefinition(ProcessEngineFactoryBean.class);
            processEngineFactoryBean.setSource(source);
            processEngineFactoryBean.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            processEngineFactoryBean.getPropertyValues().add("processEngineConfiguration", processEngineConfiguration);
            parserContext.registerBeanComponent(new BeanComponentDefinition(processEngineFactoryBean, PROCESS_ENGINE_FACTORY_BEAN));

            // registry
            RootBeanDefinition camundaRegistry = new RootBeanDefinition(BusinessProcessRegistry.class);
            camundaRegistry.setSource(source);
            camundaRegistry.setRole(BeanDefinition.ROLE_APPLICATION);
            List<String> services = Arrays.asList("RepositoryService", "RuntimeService", "TaskService", "HistoryService", "ManagementService", "FormService");
            for (String service : services) {
                registerProcessEngineService(camundaRegistry, service);
            }
            parserContext.registerBeanComponent(new BeanComponentDefinition(camundaRegistry, REGISTRY));

            // info
            logger.info("register " + REGISTRY + " & " + PROCESS_ENGINE_FACTORY_BEAN + " bean component.");

        }
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        Object source = parserContext.extractSource(element);

        if (!parserContext.getRegistry().containsBeanDefinition(BUSINESS_PROCESS_SERVICE)) {
            RootBeanDefinition rootBeanDefinition;
            parseRegistry(element, parserContext, source);
            rootBeanDefinition = new RootBeanDefinition(BusinessProcessServiceImpl.class);
            rootBeanDefinition.setScope("prototype");
            rootBeanDefinition.setSource(source);
            rootBeanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
            parserContext.getRegistry().registerBeanDefinition(BUSINESS_PROCESS_SERVICE, rootBeanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(rootBeanDefinition, BUSINESS_PROCESS_SERVICE));
            logger.info("register " + BUSINESS_PROCESS_SERVICE + " bean definition.");
        }

        return null;
    }

}
