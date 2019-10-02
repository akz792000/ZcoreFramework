/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.base.config;

import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.w3c.dom.Element;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.base.encryption.EncryptFactory;
import org.zcoreframework.base.method.MethodTemplateExecutorImpl;

import java.util.Properties;

public class DataAccessDefinitionParser implements BeanDefinitionParser {

	private static final String PREFIX = "org.zcoreframework.base.";

	public static final String DATA_SOURCE = PREFIX + "dataSource";

	public static final String ENTITY_MANAGER_FACTORY = PREFIX + "entityManagerFactory";

	public static final String TRANSACTION_MANAGER = PREFIX + "transactionManager";

	public static final String METHOD_TEMPLATE_EXECUTOR = PREFIX + "methodTemplateExecutor";

	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {

		// get source
		Object source = parserContext.extractSource(element);

		// get property value
		Properties properties = PropertiesFactoryBean.getProperties();

		// data source
		if (!parserContext.getRegistry().containsBeanDefinition(DATA_SOURCE)) {

			// basic bean definition
			RootBeanDefinition beanDefinition = new RootBeanDefinition(PoolDataSourceFactory.class);
			beanDefinition.setSource(source);
			beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			beanDefinition.setFactoryMethodName("getPoolDataSource");

			// authentication
			beanDefinition.getPropertyValues().add("URL", properties.get("zcore.base.jdbc.url"));
			beanDefinition.getPropertyValues().add("user", properties.get("zcore.base.jdbc.user"));

			beanDefinition.getPropertyValues().add("password",
					EncryptFactory.getEncrypt((String) properties.get("zcore.base.encrypt.type")).decrypt(
							(String) properties.get("zcore.base.jdbc.password") ,
							(String) properties.get("zcore.base.encrypt.secret.key"))
			);

			// connection
			beanDefinition.getPropertyValues().add("connectionFactoryClassName", "oracle.jdbc.pool.OracleDataSource");
			beanDefinition.getPropertyValues().add("connectionPoolName", "ZCORE_POOL");
			beanDefinition.getPropertyValues().add("validateConnectionOnBorrow", "true");

			// pool size configuration
			beanDefinition.getPropertyValues().add("initialPoolSize", properties.get("zcore.base.connection.initial"));
			beanDefinition.getPropertyValues().add("minPoolSize", properties.get("zcore.base.connection.min"));
			beanDefinition.getPropertyValues().add("maxPoolSize", properties.get("zcore.base.connection.max"));


			// register
			parserContext.getRegistry().registerBeanDefinition(DATA_SOURCE, beanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, DATA_SOURCE));

			// log
			logger.info("register " + DATA_SOURCE + " bean definition.");
		}

		// entity manager factory
		if (!parserContext.getRegistry().containsBeanDefinition(ENTITY_MANAGER_FACTORY)) {

			// basic bean definition
			RootBeanDefinition beanDefinition = new RootBeanDefinition(LocalContainerEntityManagerFactoryBean.class);
			beanDefinition.setSource(source);
			beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

			// set property value
			beanDefinition.getPropertyValues().add("dataSource", new RuntimeBeanReference(DATA_SOURCE));
			beanDefinition.getPropertyValues().add("persistenceUnitName", "zcore");
			beanDefinition.getPropertyValues().add("packagesToScan", "org.zcoreframework.**.domain," + properties.get("zcore.base.packagesToScan"));
			beanDefinition.getPropertyValues().add("jpaVendorAdapter", new RootBeanDefinition(EclipseLinkJpaVendorAdapter.class));
			beanDefinition.getPropertyValues().add("jpaDialect", new RootBeanDefinition(EclipseLinkJpaDialect.class));

			// load time weaver
			RootBeanDefinition loadTimeWeaver = new RootBeanDefinition();
			loadTimeWeaver.setFactoryBeanName(ContextDefinitionParser.APPLICATION_SERVER_SPECIFIC);
			loadTimeWeaver.setFactoryMethodName("getInstrumentationLoadTimeWeaver");
			beanDefinition.getPropertyValues().add("loadTimeWeaver", loadTimeWeaver);

			// properties
			Properties jpaProperties = new Properties();

			// Eclipselink Configuration
			jpaProperties.setProperty("eclipselink.target-server", "none");
			jpaProperties.setProperty("eclipselink.target-database", (String) properties.get("zcore.base.target-database"));
			jpaProperties.setProperty("eclipselink.ddl-generation", (String) properties.get("zcore.base.ddl-generation"));
			jpaProperties.setProperty("eclipselink.weaving", (String) properties.get("zcore.base.weaving"));
			jpaProperties.setProperty("eclipselink.deploy-on-startup", (String) properties.get("zcore.base.deploy-on-startup"));
			jpaProperties.setProperty("eclipselink.cache.shared.default", "false");

			// logging
			jpaProperties.setProperty("eclipselink.logging.logger", (String) properties.get("zcore.base.logging.logger"));
			jpaProperties.setProperty("eclipselink.logging.level", (String) properties.get("zcore.base.logging.level"));
			jpaProperties.setProperty("eclipselink.logging.parameters", (String) properties.get("zcore.base.logging.parameters"));
			jpaProperties.setProperty("eclipselink.logging.level.sql", (String) properties.get("zcore.base.logging.level.sql"));
			jpaProperties.setProperty("eclipselink.logging.timestamp", (String) properties.get("zcore.base.logging.timestamp"));
			jpaProperties.setProperty("eclipselink.logging.session", (String) properties.get("zcore.base.logging.session"));
			jpaProperties.setProperty("eclipselink.logging.thread", (String) properties.get("zcore.base.logging.thread"));
			jpaProperties.setProperty("eclipselink.logging.exceptions", (String) properties.get("zcore.base.logging.exceptions"));

			// persistence validation mode
			jpaProperties.setProperty("javax.persistence.validation.mode", (String) properties.get("zcore.base.validation.mode"));

			// Eclipselink Cache Configuration
			jpaProperties.setProperty("eclipselink.cache.type.default", (String) properties.get("zcore.base.cache.type.default"));
			jpaProperties.setProperty("eclipselink.cache.size.default", (String) properties.get("zcore.base.cache.size.default"));

			// Set Unicode and characterEncoding
			jpaProperties.setProperty("useUnicode", "true");
			jpaProperties.setProperty("characterEncoding", "UTF-8");

			// set allow zero id  valid  a entity
			jpaProperties.setProperty("eclipselink.allow-zero-id",(String) properties.get("zcore.base.allow.zero.id"));

			// set jpa properties
			beanDefinition.getPropertyValues().add("jpaProperties", jpaProperties);



			// register
			parserContext.getRegistry().registerBeanDefinition(ENTITY_MANAGER_FACTORY, beanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, ENTITY_MANAGER_FACTORY));

			// log
			logger.info("register " + ENTITY_MANAGER_FACTORY + " bean definition.");
		}

		// transaction manager
		if (!parserContext.getRegistry().containsBeanDefinition(TRANSACTION_MANAGER)) {

			// basic bean definition
			RootBeanDefinition beanDefinition = new RootBeanDefinition();
			beanDefinition.setSource(source);
			beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			beanDefinition.setFactoryBeanName(ContextDefinitionParser.APPLICATION_SERVER_SPECIFIC);
			beanDefinition.setFactoryMethodName("getTransactionManager");

			// constructor argument
			ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
			constructorArgumentValues.addIndexedArgumentValue(0, new RuntimeBeanReference(ENTITY_MANAGER_FACTORY));
			beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

			// register
			parserContext.getRegistry().registerBeanDefinition(TRANSACTION_MANAGER, beanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, TRANSACTION_MANAGER));

			// log
			logger.info("register " + TRANSACTION_MANAGER + " bean definition.");
		}

		// method template executor
		if (!parserContext.getRegistry().containsBeanDefinition(METHOD_TEMPLATE_EXECUTOR)) {

			// basic bean definition
			RootBeanDefinition beanDefinition = new RootBeanDefinition(MethodTemplateExecutorImpl.class);
			beanDefinition.setSource(source);
			beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);

			// constructor argument
			ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
			constructorArgumentValues.addIndexedArgumentValue(0, new RuntimeBeanReference(TRANSACTION_MANAGER));
			beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

			// register
			parserContext.getRegistry().registerBeanDefinition(METHOD_TEMPLATE_EXECUTOR, beanDefinition);
			parserContext.registerComponent(new BeanComponentDefinition(beanDefinition, METHOD_TEMPLATE_EXECUTOR));

			// log
			logger.info("register " + METHOD_TEMPLATE_EXECUTOR + " bean definition.");
		}

		return null;
	}

}
