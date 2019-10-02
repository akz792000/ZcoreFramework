/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

abstract class MvcNamespaceUtils {

	/*
	<ZANDI>
	private static final String BEAN_NAME_URL_HANDLER_MAPPING_BEAN_NAME =
			BeanNameUrlHandlerMapping.class.getName();

	private static final String SIMPLE_CONTROLLER_HANDLER_ADAPTER_BEAN_NAME =
			SimpleControllerHandlerAdapter.class.getName();
	</ZANDI>
	*/		

	private static final String HTTP_REQUEST_HANDLER_ADAPTER_BEAN_NAME =
			HttpRequestHandlerAdapter.class.getName();

	public static void registerDefaultComponents(ParserContext parserContext, Object source) {
		//registerBeanNameUrlHandlerMapping(parserContext, source);
		registerHttpRequestHandlerAdapter(parserContext, source);
		//registerSimpleControllerHandlerAdapter(parserContext, source); <ZANDI>
	}

	/**
	 * Registers  an {@link HttpRequestHandlerAdapter} under a well-known
	 * name unless already registered.
	 */
	/*
	<ZANDI>
	private static void registerBeanNameUrlHandlerMapping(ParserContext parserContext, Object source) {
		if (!parserContext.getRegistry().containsBeanDefinition(BEAN_NAME_URL_HANDLER_MAPPING_BEAN_NAME)){
			RootBeanDefinition beanNameMappingDef = new RootBeanDefinition(BeanNameUrlHandlerMapping.class);
			beanNameMappingDef.setSource(source);
			beanNameMappingDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			beanNameMappingDef.getPropertyValues().add("order", 2);	// consistent with WebMvcConfigurationSupport
			parserContext.getRegistry().registerBeanDefinition(BEAN_NAME_URL_HANDLER_MAPPING_BEAN_NAME, beanNameMappingDef);
			parserContext.registerComponent(new BeanComponentDefinition(beanNameMappingDef, BEAN_NAME_URL_HANDLER_MAPPING_BEAN_NAME));
		}
	}
	</ZANDI>
	*/

	/**
	 * Registers  an {@link HttpRequestHandlerAdapter} under a well-known
	 * name unless already registered.
	 */
	private static void registerHttpRequestHandlerAdapter(ParserContext parserContext, Object source) {
		if (!parserContext.getRegistry().containsBeanDefinition(HTTP_REQUEST_HANDLER_ADAPTER_BEAN_NAME)) {
			RootBeanDefinition handlerAdapterDef = new RootBeanDefinition(HttpRequestHandlerAdapter.class);
			handlerAdapterDef.setSource(source);
			handlerAdapterDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			parserContext.getRegistry().registerBeanDefinition(HTTP_REQUEST_HANDLER_ADAPTER_BEAN_NAME, handlerAdapterDef);
			parserContext.registerComponent(new BeanComponentDefinition(handlerAdapterDef, HTTP_REQUEST_HANDLER_ADAPTER_BEAN_NAME));
		}
	}

	/**
	 * Registers a {@link SimpleControllerHandlerAdapter} under a well-known
	 * name unless already registered.
	 */
	/*
	<ZANDI>	
	private static void registerSimpleControllerHandlerAdapter(ParserContext parserContext, Object source) {
		if (!parserContext.getRegistry().containsBeanDefinition(SIMPLE_CONTROLLER_HANDLER_ADAPTER_BEAN_NAME)) {
			RootBeanDefinition handlerAdapterDef = new RootBeanDefinition(SimpleControllerHandlerAdapter.class);
			handlerAdapterDef.setSource(source);
			handlerAdapterDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			parserContext.getRegistry().registerBeanDefinition(SIMPLE_CONTROLLER_HANDLER_ADAPTER_BEAN_NAME, handlerAdapterDef);
			parserContext.registerComponent(new BeanComponentDefinition(handlerAdapterDef, SIMPLE_CONTROLLER_HANDLER_ADAPTER_BEAN_NAME));
		}
	}
	</ZANDI>
	*/
	
}