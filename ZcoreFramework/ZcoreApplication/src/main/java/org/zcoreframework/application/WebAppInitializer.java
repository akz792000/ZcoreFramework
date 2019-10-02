package org.zcoreframework.application;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;
import org.zcoreframework.web.servlet.resource.ResourceServlet;

@SuppressWarnings("deprecation")
public class WebAppInitializer implements WebApplicationInitializer  {

	@Override
	public void onStartup(ServletContext container) {

		/*
		 * context-param
		 */
		container.setInitParameter("log4jConfigLocation", "classpath:META-INF/log4j.xml");
		container.setInitParameter("log4jExposeWebAppRoot", "false");
		container.setInitParameter("contextConfigLocation", "classpath:META-INF/rootContext.xml");
		
		/*
		 * listeners
		 */
		container.addListener(SessionListener.class);
		container.addListener(Log4jConfigListener.class);		
		container.addListener(HttpSessionEventPublisher.class);
		container.addListener(ContextLoaderListener.class);
		
		/*
		 * character encoding filter
		 */
		FilterRegistration.Dynamic encodingFilter = container.addFilter("encoding-filter", new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "false");
		encodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");	
		
		/*
		 * spring security filter 
		 */
		FilterRegistration.Dynamic filter = container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
		filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*");		
		
		/*
		 * resource
		 */
		ServletRegistration.Dynamic resource = container.addServlet("resource", new ResourceServlet());
		resource.setInitParameter("gzipEnabled", "true");
		resource.setLoadOnStartup(1);
		resource.addMapping("/resources/*");		

		/*
		 * main
		 */
		ServletRegistration.Dynamic dispatcher = container.addServlet("main", new DispatcherServlet());
		dispatcher.setInitParameter("contextConfigLocation", "");
		dispatcher.setLoadOnStartup(2);
		dispatcher.addMapping("/");

	}
}