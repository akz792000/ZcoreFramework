/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.core;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.util.ReflectionUtils;

public class PropertiesFactoryBean extends PropertiesLoaderSupport implements FactoryBean<Properties>, InitializingBean {

	public static String LOCATION = "META-INF/config.properties";
	public static String FILE_ENCODING = "UTF-8";
	
	private final Log logger = LogFactory.getLog(getClass());

	private static PropertiesFactoryBean instance;

	private Properties singletonInstance;

	private PropertiesFactoryBean() {

	}

	public static PropertiesFactoryBean getPropertiesFactoryBean() {
		if (instance == null) {
			String location = (String) System.getProperties().get("zcore.base.location");
			instance = new PropertiesFactoryBean();
			instance.setLocation(new ClassPathResource(StringUtils.isEmpty(location) ? LOCATION : location));
			instance.setFileEncoding(FILE_ENCODING);
			try {
				instance.singletonInstance = instance.createProperties();
			} catch (IOException e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
		return instance;
	}

	public static Properties getProperties() {
		try {
			return getPropertiesFactoryBean().getObject();
		} catch (IOException e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return null;
	}

	@Override
	public final boolean isSingleton() {
		return true;
	}

	@Override
	public final void afterPropertiesSet() throws IOException {
		logger.info("after properties set for PropertiesFactoryBean.");
	}

	@Override
	public final Properties getObject() throws IOException {
		return this.singletonInstance;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	protected Properties createProperties() throws IOException {
		return mergeProperties();
	}

}
