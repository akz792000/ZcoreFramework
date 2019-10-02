package org.zcoreframework.pos.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.scheduling.config.AnnotationDrivenBeanDefinitionParser;
import org.springframework.scheduling.config.ExecutorBeanDefinitionParser;

/**
 *
 */
public class ISOServerNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("isoServer", new ISOServerDefinitionParser());
        registerBeanDefinitionParser("executor-annotation", new AnnotationDrivenBeanDefinitionParser());
        registerBeanDefinitionParser("executor", new ExecutorBeanDefinitionParser());
    }
}
