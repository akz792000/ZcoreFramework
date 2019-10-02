/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.base.util;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.StandardMethodMetadata;
import org.zcoreframework.base.component.Component;

public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextUtils.applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return ApplicationContextUtils.getApplicationContext().getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return ApplicationContextUtils.getApplicationContext().getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return ApplicationContextUtils.getApplicationContext().getBean(requiredType);
    }

    public static <T> T createBean(Class<T> beanClass) {
        return ApplicationContextUtils.getApplicationContext().getAutowireCapableBeanFactory().createBean(beanClass);
    }

    public static Map<String, Class<?>> getBeanNamesForAnnotation(Class<? extends Annotation> type) throws ClassNotFoundException {
        ConfigurableListableBeanFactory factory = (ConfigurableListableBeanFactory) ApplicationContextUtils.getApplicationContext().getAutowireCapableBeanFactory();
        Map<String, Class<?>> results = new HashMap<>();
        for (String beanName : factory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            if (!beanDefinition.isAbstract() && factory.findAnnotationOnBean(beanName, type) != null) {
                results.put(beanName, Class.forName(beanDefinition.getBeanClassName()));
            }
        }
        return results;
    }

    public static Component createComponent(Class<?> componentClass, Annotation annotation, String name) throws InstantiationException, IllegalAccessException {
        AutowireCapableBeanFactory beanFactory = (AutowireCapableBeanFactory) ApplicationContextUtils.getBeanFactory();
        Component component = (Component) componentClass.newInstance();
        component.setAnnotation(annotation);
        beanFactory.autowireBean(component);
        beanFactory.initializeBean(component, name);
        component.init();
        return component;
    }

    public static <T> Object createBean(Class<?> beanClass, int autowireMode, boolean dependencyCheck) {
        return ApplicationContextUtils.getApplicationContext().getAutowireCapableBeanFactory().createBean(beanClass, autowireMode, dependencyCheck);
    }

    public static AutowireCapableBeanFactory getBeanFactory() {
        return ApplicationContextUtils.getApplicationContext().getAutowireCapableBeanFactory();
    }

    public static Resource getResource(String location) {
        return ApplicationContextUtils.getApplicationContext().getResource(location);
    }

    public static <T> T autowireInitBean(T object, String name) {
        AutowireCapableBeanFactory beanFactory = ApplicationContextUtils.getBeanFactory();
        beanFactory.autowireBean(object);
        beanFactory.initializeBean(object, name);
        return object;
    }

    public static <T> T autowireInitBean(T object) {
        return autowireInitBean(object, "");
    }

}
