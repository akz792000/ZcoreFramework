package org.zcoreframework.batch.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.w3c.dom.Element;
import org.zcoreframework.base.config.DataAccessDefinitionParser;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.base.util.ConstantsUtils.Schema;
import org.zcoreframework.batch.util.TransactionManagerUtils;

import java.util.Properties;

import static org.zcoreframework.base.config.DataAccessDefinitionParser.TRANSACTION_MANAGER;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 5/7/17
 */
public class BatchConfigDefinitionParser implements BeanDefinitionParser {

    private static final Log LOGGER = LogFactory.getLog(BatchConfigDefinitionParser.class);

    private static final String PREFIX = "org.zcoreframework.batch.";

    public static final String JOB_REPOSITORY = /* PREFIX + */ "jobRepository";

    public static final String IN_MEMORY_JOB_REPOSITORY = /* PREFIX + */ "inMemoryJobRepository";

    public static final String JOB_LAUNCHER = /* PREFIX + */ "jobLauncher";

    public static final String TASK_EXECUTOR = /* PREFIX + */ "taskExecutor";

    public static final String TRANSACTION_MANAGER_UTILS = PREFIX + "transactionManagerUtils";

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        // get source
        Object source = parserContext.extractSource(element);

        // get property value
        Properties properties = PropertiesFactoryBean.getProperties();

        // job repository
        if (!parserContext.getRegistry().containsBeanDefinition(JOB_REPOSITORY)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(JobRepositoryFactoryBean.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            beanDefinition.getPropertyValues().add("transactionManager", new RuntimeBeanReference(TRANSACTION_MANAGER));
            beanDefinition.getPropertyValues().add("dataSource", new RuntimeBeanReference(DataAccessDefinitionParser.DATA_SOURCE));
            beanDefinition.getPropertyValues().add("isolationLevelForCreate", "ISOLATION_DEFAULT");
            beanDefinition.getPropertyValues().add("tablePrefix", String.format("%s.%s", Schema.BH, properties.get("zcore.batch.tablePrefix")));
            // register
            parserContext.getRegistry().registerBeanDefinition(JOB_REPOSITORY, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, JOB_REPOSITORY));

            // log
            LOGGER.info("register " + JOB_REPOSITORY + " bean definition.");
        }

        // in memory job repository
        if (!parserContext.getRegistry().containsBeanDefinition(IN_MEMORY_JOB_REPOSITORY)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(MapJobRepositoryFactoryBean.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            beanDefinition.getPropertyValues().add("transactionManager", new RuntimeBeanReference(TRANSACTION_MANAGER));

            // register
            parserContext.getRegistry().registerBeanDefinition(IN_MEMORY_JOB_REPOSITORY, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, IN_MEMORY_JOB_REPOSITORY));

            // log
            LOGGER.info("register " + IN_MEMORY_JOB_REPOSITORY + " bean definition.");
        }

        // job launcher
        if (!parserContext.getRegistry().containsBeanDefinition(JOB_LAUNCHER)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(SimpleJobLauncher.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            beanDefinition.getPropertyValues().add("jobRepository", new RuntimeBeanReference(JOB_REPOSITORY));

            // register
            parserContext.getRegistry().registerBeanDefinition(JOB_LAUNCHER, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, JOB_LAUNCHER));

            // log
            LOGGER.info("register " + JOB_LAUNCHER + " bean definition.");
        }

        // task executor
        if (!parserContext.getRegistry().containsBeanDefinition(TASK_EXECUTOR)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(SimpleAsyncTaskExecutor.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            // register
            parserContext.getRegistry().registerBeanDefinition(TASK_EXECUTOR, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, TASK_EXECUTOR));

            // log
            LOGGER.info("register " + TASK_EXECUTOR + " bean definition.");
        }

        // transaction manager utils
        if (!parserContext.getRegistry().containsBeanDefinition(TRANSACTION_MANAGER_UTILS)) {

            // basic bean definition
            RootBeanDefinition beanDefinition = new RootBeanDefinition(TransactionManagerUtils.class);
            beanDefinition.setSource(source);
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

            // set property value
            beanDefinition.getPropertyValues().add("transactionManager", new RuntimeBeanReference(TRANSACTION_MANAGER));

            // register
            parserContext.getRegistry().registerBeanDefinition(TRANSACTION_MANAGER_UTILS, beanDefinition);
            parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, TRANSACTION_MANAGER_UTILS));

            // log
            LOGGER.info("register " + TRANSACTION_MANAGER_UTILS + " bean definition.");
        }

        return null;
    }
}
