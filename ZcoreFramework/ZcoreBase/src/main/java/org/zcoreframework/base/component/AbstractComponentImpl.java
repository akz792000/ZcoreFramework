/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.base.util.ApplicationContextUtils;

public abstract class AbstractComponentImpl extends PropertyBindValue implements Component {

	public static enum AnnotationReturnType {
		OBJECT, LIST
	};

	private Annotation annotation;

	@Override
	public Annotation getAnnotation() {
		return annotation;
	}

	@Override
	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

	/*
	 * @see org.zcoreframework.base.component.Component#processAnnotation()
	 */

	@Override
	@PostConstruct
	public void processAnnotation() throws Throwable {
		if (annotation != null) {
			BeanWrapper beanWrapper = new BeanWrapperImpl(this);
			for (Method method : annotation.annotationType().getDeclaredMethods()) {
				Class<?> type = method.getReturnType();
				AnnotationReturnType annotationReturnType = AnnotationReturnType.OBJECT;
				if (type.isArray()) {
					type = type.getComponentType();
					annotationReturnType = AnnotationReturnType.LIST;
				}
				ComponentType componentType = type.getAnnotation(ComponentType.class);
				Object object;
				if (componentType != null) {
					switch (annotationReturnType) {
					case LIST:
						List<Component> items = new LinkedList<>();
						for (Annotation componentAnnotation : (Annotation[]) method.invoke(annotation)) {
							items.add(ApplicationContextUtils.createComponent(componentType.clazz(), componentAnnotation, ""));
						}
						object = items;
						break;
					default:
						object = ApplicationContextUtils.createComponent(componentType.clazz(), (Annotation) method.invoke(annotation), method.getName());
						break;
					}
				} else {
					object = method.invoke(annotation);
				}
				beanWrapper.setPropertyValue(method.getName(), object);
			}
		}
	}

	@Override
	public void init() {
		// initialize component
	}

	@Override
	public void clean() {
		// clean component
	}

	@Override
	public Object parseValue(String text) {
		return text;
	}

	@Override
	public void bindValue(Object object) {
		// bind value
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
