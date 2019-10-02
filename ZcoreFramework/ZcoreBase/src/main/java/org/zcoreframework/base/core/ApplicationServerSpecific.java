/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.core;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.glassfish.GlassFishLoadTimeWeaver;
import org.springframework.instrument.classloading.jboss.JBossLoadTimeWeaver;
import org.springframework.instrument.classloading.weblogic.WebLogicLoadTimeWeaver;
import org.springframework.instrument.classloading.websphere.WebSphereLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

public class ApplicationServerSpecific implements BeanClassLoaderAware {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	public enum ApplicationServerType {
		TOMCAT, WEBSPHERE, WEBLOGIC, JBOSS, GLASSFISH, DEFAULT
	};
	
	private ClassLoader classLoader;
	
	private ApplicationServerType applicationServerType;
	
	public ClassLoader getClassLoader() {
		return classLoader;
	}
	
	public ApplicationServerType getApplicationServerType() {
		return applicationServerType;
	}
	
	public AbstractPlatformTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}

	public LoadTimeWeaver getInstrumentationLoadTimeWeaver() {
		switch (applicationServerType) {
		case WEBSPHERE:
			return new WebSphereLoadTimeWeaver(classLoader);
		case GLASSFISH:
			return new GlassFishLoadTimeWeaver(classLoader);
		case WEBLOGIC:
			return new WebLogicLoadTimeWeaver(classLoader);
		case JBOSS:
			return new JBossLoadTimeWeaver(classLoader); 
		case TOMCAT:
		case DEFAULT:
		default:
			return new InstrumentationLoadTimeWeaver();
		}
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
		try {
			String name = classLoader.getClass().getName();
			if (name.startsWith("weblogic")) {
				applicationServerType = ApplicationServerType.WEBLOGIC;
			} else if (name.startsWith("org.glassfish")) {
				applicationServerType = ApplicationServerType.GLASSFISH;
			} else if (name.startsWith("org.jboss")) {
				applicationServerType = ApplicationServerType.JBOSS;
			} else if (name.startsWith("com.ibm")) {
				applicationServerType = ApplicationServerType.WEBSPHERE;
			} else if (name.startsWith("org.apache.catalina")) {
				applicationServerType = ApplicationServerType.TOMCAT;				
			} else {
				applicationServerType = ApplicationServerType.DEFAULT;
			}
		} catch (IllegalStateException ex) {
			logger.info("Could not obtain server-specific : " + ex.getMessage());
			applicationServerType = null;
		}
		
	}
	
}
