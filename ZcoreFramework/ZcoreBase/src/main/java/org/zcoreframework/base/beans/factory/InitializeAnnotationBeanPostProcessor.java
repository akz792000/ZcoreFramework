/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.beans.factory;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.annotation.Clone;
import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.base.beans.factory.annotation.Create;
import org.zcoreframework.base.beans.factory.annotation.Register;
import org.zcoreframework.base.component.Component;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.base.data.RepositoryConstructor;
import org.zcoreframework.base.data.RepositoryFactoryBean;
import org.zcoreframework.base.induction.FactoryInductionHandler;
import org.zcoreframework.base.induction.Induct;
import org.zcoreframework.base.induction.Induct.InductionType;
import org.zcoreframework.base.util.ArrayList;

public class InitializeAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements MergedBeanDefinitionPostProcessor,
		PriorityOrdered, BeanFactoryAware {

	protected final Log logger = LogFactory.getLog(getClass());

	private int order = Ordered.LOWEST_PRECEDENCE - 4;

	private final Set<Class<? extends Annotation>> initializeAnnotationTypes = new LinkedHashSet<Class<? extends Annotation>>();

	private final Map<Class<?>, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<Class<?>, InjectionMetadata>(64);

	private ConfigurableListableBeanFactory beanFactory;

	public InitializeAnnotationBeanPostProcessor() {
		this.initializeAnnotationTypes.add(Create.class);
		this.initializeAnnotationTypes.add(Clone.class);
		this.initializeAnnotationTypes.add(Register.class);
		this.initializeAnnotationTypes.add(Induct.class);
		this.initializeAnnotationTypes.add(RepositoryInstance.class);
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return this.order;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
			throw new IllegalArgumentException("InitializeAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory");
		}
		this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
	}

	private Annotation findInitializeAnnotation(AccessibleObject ao) {
		for (Class<? extends Annotation> type : this.initializeAnnotationTypes) {
			Annotation annotation = AnnotationUtils.getAnnotation(ao, type);
			if (annotation != null) {
				return annotation;
			}
		}
		return null;
	}

	private InjectionMetadata buildAutowiringMetadata(Class<?> clazz, String beanName) {
		LinkedList<InjectionMetadata.InjectedElement> elements = new LinkedList<InjectionMetadata.InjectedElement>();
		Class<?> targetClass = clazz;
		do {
			LinkedList<InjectionMetadata.InjectedElement> currElements = new LinkedList<InjectionMetadata.InjectedElement>();
			for (Field field : targetClass.getDeclaredFields()) {
				Annotation annotation = findInitializeAnnotation(field);
				if (annotation != null) {
					if (Modifier.isStatic(field.getModifiers())) {
						if (logger.isWarnEnabled()) {
							logger.warn("Initialize annotation is not supported on static fields: " + field);
						}
						continue;
					}
					/*
					 * add inject field element
					 */
					// register
					if (annotation.annotationType().equals(Register.class)) {
						currElements.add(new RegisterFieldElement(field, (Register) annotation));
						// create
					} else if (annotation.annotationType().equals(Create.class)) {
						currElements.add(new CreateFieldElement(field, (Create) annotation));
						// clone
					} else if (annotation.annotationType().equals(Clone.class)) {
						currElements.add(new CloneFieldElement(field, (Clone) annotation));
						// induct
					} else if (annotation.annotationType().equals(Induct.class)) {
						currElements.add(new InductFieldElement(field, (Induct) annotation));
						// repository
					} else if (annotation.annotationType().equals(RepositoryInstance.class)) {
						currElements.add(new RepositoryFieldElement(field, (RepositoryInstance) annotation));
					}
				}
			}
			for (Method method : targetClass.getDeclaredMethods()) {
				Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
				Annotation annotation = BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod) ? findInitializeAnnotation(bridgedMethod)
						: findInitializeAnnotation(method);
				if (annotation != null && method.equals(ClassUtils.getMostSpecificMethod(method, clazz))) {
					if (Modifier.isStatic(method.getModifiers())) {
						if (logger.isWarnEnabled()) {
							logger.warn("Autowired annotation is not supported on static methods: " + method);
						}
						continue;
					}
					if (method.getParameterTypes().length == 0) {
						if (logger.isWarnEnabled()) {
							logger.warn("Autowired annotation should be used on methods with actual parameters: " + method);
						}
					}
					if (annotation.annotationType().equals(Create.class)) {
						PropertyDescriptor pd = BeanUtils.findPropertyForMethod(method);
						currElements.add(new CreateMethodElement(method, pd));
					}
				}
			}
			elements.addAll(0, currElements);
			targetClass = targetClass.getSuperclass();
		} while (targetClass != null && targetClass != Object.class);
		return new InjectionMetadata(clazz, elements);
	}

	private InjectionMetadata findAutowiringMetadata(Class<?> clazz, String beanName) {
		InjectionMetadata metadata = this.injectionMetadataCache.get(clazz);
		if (metadata == null) {
			synchronized (this.injectionMetadataCache) {
				metadata = this.injectionMetadataCache.get(clazz);
				if (metadata == null) {
					metadata = buildAutowiringMetadata(clazz, beanName);
					this.injectionMetadataCache.put(clazz, metadata);
				}
			}
		}
		return metadata;
	}

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (beanType.equals(InitializeAware.class)) {
			InjectionMetadata metadata = findAutowiringMetadata(beanType, beanName);
			metadata.checkConfigMembers(beanDefinition);
		}
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		if (bean instanceof InitializeAware) {
			InjectionMetadata metadata = findAutowiringMetadata(bean.getClass(), beanName);
			try {
				metadata.inject(bean, beanName, pvs);
			} catch (Throwable ex) {
				throw new BeanCreationException(beanName, "Injection of initialize dependencies failed", ex);
			}
		}
		return pvs;
	}

	/*
	 * Abstract
	 */

	private interface Triggerable {
		abstract Object trigger() throws Throwable;
	}

	private abstract class ComponentElement<T extends Annotation> implements Triggerable {

		private T annotation;

		private Annotation componentTypeAnnotation;

		private Class<?> componentTypeClass;

		public T getAnnotation() {
			return annotation;
		}

		public Annotation getComponentTypeAnnotation() {
			return componentTypeAnnotation;
		}

		public Class<?> getComponentTypeClass() {
			return componentTypeClass;
		}

		public ComponentElement(T annotation) {
			this.annotation = annotation;
		}

		public ComponentElement(T annotation, Annotation componentTypeAnnotation, Class<?> componentTypeClass) {
			this(annotation);
			this.componentTypeAnnotation = componentTypeAnnotation;
			this.componentTypeClass = componentTypeClass;
		}

	}

	private abstract class NamedComponentElement<T extends Annotation> extends ComponentElement<T> {

		private String name;

		public String getName() {
			return name;
		}

		protected void provideName(T annotation, String name) {
			try {
				this.name = (String) annotation.annotationType().getMethod("value").invoke(annotation);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				this.name = "";
			}
			this.name = StringUtils.isEmpty(this.name) ? name : this.name;
		}

		public NamedComponentElement(T annotation, String name) {
			super(annotation);
			provideName(annotation, name);
		}

		public NamedComponentElement(T annotation, String name, Annotation componentTypeAnnotation, Class<?> componentTypeClass) {
			super(annotation, componentTypeAnnotation, componentTypeClass);
			provideName(annotation, name);
		}

	}

	/*
	 * Clone Component Element
	 */

	private class CloneComponentElement extends NamedComponentElement<Clone> {

		public CloneComponentElement(Clone annotation, String name) {
			super(annotation, name);
		}

		public Object trigger() throws Throwable {
			return ((Component) beanFactory.getBean(getName())).clone();
		}

	}

	/*
	 * Clone Field Element
	 */

	private class CloneFieldElement extends InjectionMetadata.InjectedElement {

		private final Triggerable componentElement;

		protected CloneFieldElement(Field field, Clone annotation) {
			super(field, null);
			componentElement = new CloneComponentElement(annotation, field.getName());
		}

		@Override
		protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
			Field field = (Field) this.member;
			try {
				Object value = componentElement.trigger();
				if (value != null) {
					ReflectionUtils.makeAccessible(field);
					field.set(bean, value);
				}
			} catch (Throwable ex) {
				throw new BeanCreationException("Could not clone field: " + field, ex);
			}
		}
	}

	/*
	 * Register Component Element
	 */

	private class RegisterComponentElement extends NamedComponentElement<Register> {

		public RegisterComponentElement(Register annotation, String name, Annotation componentTypeAnnotation, Class<?> componentTypeClass) {
			super(annotation, name, componentTypeAnnotation, componentTypeClass);
		}

		public Object trigger() throws Throwable {
			if (!beanFactory.containsBean(getName())) {
				RootBeanDefinition beanDefinition = new RootBeanDefinition();
				beanDefinition.setBeanClass(getComponentTypeClass());
				beanDefinition.setScope(getAnnotation().scope());
				// set property values
				MutablePropertyValues propertyValues = new MutablePropertyValues();
				propertyValues.addPropertyValue("annotation", getComponentTypeAnnotation());
				beanDefinition.setPropertyValues(propertyValues);
				// set init-method
				if (getAnnotation().init()) {
					beanDefinition.setInitMethodName("init");
				}
				// register bean in the application context
				BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) beanFactory;
				beanDefinitionRegistry.registerBeanDefinition(getName(), beanDefinition);
			} else {
				throw new Exception("application context has already registered this field bean name: " + getName());
			}
			return true;
		}

	}

	/*
	 * Register Field Element
	 */

	private class RegisterFieldElement extends InjectionMetadata.InjectedElement {

		private final Triggerable componentElement;

		public RegisterFieldElement(Field field, Register annotation) {
			super(field, null);
			ComponentType componentType = AnnotationUtils.getAnnotation(field, ComponentType.class);
			Annotation componentTypeAnnotation = AnnotationUtils.getAnnotation(field, componentType.annotation());
			componentElement = new RegisterComponentElement(annotation, field.getName(), componentTypeAnnotation, componentType.clazz());
		}

		@Override
		protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
			synchronized (this) {
				componentElement.trigger();
			}
		}
	}

	/*
	 * Create Component Element
	 */

	private class CreateComponentElement extends ComponentElement<Create> {

		public CreateComponentElement(Create annotation, Annotation componentTypeAnnotation, Class<?> componentTypeClass) {
			super(annotation, componentTypeAnnotation, componentTypeClass);
		}

		public Object trigger() throws Throwable {
			/*
			 * Instantiate a new bean instance of the given class with the
			 * specified autowire strategy inject all field of bean that
			 * specific
			 */
			Component component = (Component) getComponentTypeClass().newInstance();
			component.setAnnotation(getComponentTypeAnnotation());

			/*
			 * Initialize the given raw bean, applying factory callbacks such as
			 * setBeanName and setBeanFactory, also applying all bean post
			 * processors (including ones which might wrap the given raw bean).
			 */
			beanFactory.autowireBean(component);
			beanFactory.initializeBean(component, "");

			// init
			if (getAnnotation().init()) {
				component.init();
			}
			return component;
		}

	}

	/*
	 * Create Field Element
	 */

	private class CreateFieldElement extends InjectionMetadata.InjectedElement {

		private final Triggerable componentElement;

		public CreateFieldElement(Field field, Create annotation) {
			super(field, null);
			ComponentType componentType = AnnotationUtils.getAnnotation(field, ComponentType.class);
			Annotation componentTypeAnnotation = AnnotationUtils.getAnnotation(field, componentType.annotation());
			componentElement = new CreateComponentElement(annotation, componentTypeAnnotation, componentType.clazz());
		}

		@Override
		protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
			Field field = (Field) this.member;
			try {
				Object component = componentElement.trigger();
				if (component != null) {
					ReflectionUtils.makeAccessible(field);
					field.set(bean, component);
				}
			} catch (Throwable ex) {
				throw new BeanCreationException("Could not create field: " + field, ex);
			}
		}
	}

	/*
	 * Induct Field Element
	 */

	private class InductFieldElement extends InjectionMetadata.InjectedElement {

		private final static String INDUCTION_KEY_PARAMETER = "zcore.service.induction";

		private InductionType type;

		public InductFieldElement(Field field, Induct annotation) {
			super(field, null);
			type = annotation.type();
			if (InductionType.AUTO.equals(type)) {
				String inductionType = (String) PropertiesFactoryBean.getProperties().get(INDUCTION_KEY_PARAMETER);
				if (StringUtils.isEmpty(inductionType)) {
					inductionType = "BEAN";
				}
				type = InductionType.valueOf(inductionType.toUpperCase());
			}
		}

		@Override
		protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
			Field field = (Field) this.member;
			try {
				Object object = FactoryInductionHandler.getInductionHandler(type, field.getType());
				if (object != null) {
					ReflectionUtils.makeAccessible(field);
					field.set(bean, object);
				}
			} catch (Throwable ex) {
				throw new BeanCreationException("Could not induct field: " + field, ex);
			}
		}
	}

	/*
	 * Repository Field Element
	 */

	private class RepositoryFieldElement extends InjectionMetadata.InjectedElement {

		private final RepositoryConstructor repositoryConstructor;

		public RepositoryFieldElement(Field field, RepositoryInstance annotation) {
			super(field, null);
			repositoryConstructor = RepositoryFactoryBean.getRepositoryConstructor(field, annotation.clazz());
		}

		@Override
		protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
			Field field = (Field) this.member;
			try {
				/*
				 * Instantiate a new bean instance of the given class with the
				 * specified autowire strategy inject all field of bean that
				 * specific
				 */
				Class<?> argument = repositoryConstructor.getArgument();
				Object object = argument == null ? repositoryConstructor.getConstructor().newInstance() : repositoryConstructor.getConstructor().newInstance(
						argument);

				/*
				 * Initialize the given raw bean, applying factory callbacks
				 * such as setBeanName and setBeanFactory, also applying all
				 * bean post processors (including ones which might wrap the
				 * given raw bean).
				 */
				beanFactory.autowireBean(object);
				beanFactory.initializeBean(object, "");

				// set object
				if (object != null) {
					ReflectionUtils.makeAccessible(field);
					field.set(bean, object);
				}
			} catch (Throwable ex) {
				throw new BeanCreationException("Could not repository field: " + field, ex);
			}
		}
	}

	/*
	 * Create Method Element
	 */

	private class CreateMethodElement extends InjectionMetadata.InjectedElement {

		private final List<Triggerable> componentElements = new ArrayList<>();

		private final int parameterCounts;

		public CreateMethodElement(Method method, PropertyDescriptor pd) {
			super(method, pd);
			parameterCounts = method.getParameterTypes().length;
			Annotation[][] paramAnnotations = method.getParameterAnnotations();
			for (int i = 0; i < parameterCounts; i++) {
				Annotation[] paramAnnotation = paramAnnotations[i];
				Create annotation = org.zcoreframework.base.util.ReflectionUtils.getAnnotation(paramAnnotation, Create.class);
				if (annotation != null) {
					/*
					 * for get name of method use below MethodParameter
					 * methodParam = new MethodParameter(method, i);
					 */
					ComponentType componentType = org.zcoreframework.base.util.ReflectionUtils.getAnnotation(paramAnnotation, ComponentType.class);
					Annotation componentTypeAnnotation = org.zcoreframework.base.util.ReflectionUtils
							.getAnnotation(paramAnnotation, componentType.annotation());
					componentElements.add(new CreateComponentElement(annotation, componentTypeAnnotation, componentType.clazz()));
				}
			}

		}

		@Override
		protected void inject(Object bean, String beanName, PropertyValues pvs) throws Throwable {
			if (checkPropertySkipping(pvs)) {
				return;
			}
			Method method = (Method) this.member;
			try {
				Object[] arguments = new Object[parameterCounts];
				for (int i = 0; i < parameterCounts; i++) {
					arguments[i] = componentElements.get(i).trigger();
				}
				if (arguments != null) {
					ReflectionUtils.makeAccessible(method);
					method.invoke(bean, arguments);
				}
			} catch (InvocationTargetException ex) {
				throw ex.getTargetException();
			} catch (Throwable ex) {
				throw new BeanCreationException("Could not initialize method: " + method, ex);
			}
		}

	}

}