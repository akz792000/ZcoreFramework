package org.zcoreframework.base.data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ResolvableType;
import org.springframework.util.Assert;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.data.base.GenericRepository;
import org.zcoreframework.base.data.impl.DefaultRepositoryImpl;
import org.zcoreframework.base.data.impl.GenericRepositoryImpl;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.base.util.ReflectionUtils;

public class RepositoryFactoryBean {

	private static Map<Class<?>, Class<?>> repositories = new HashMap<>();

	static {
		repositories.put(DefaultRepository.class, DefaultRepositoryImpl.class);
		repositories.put(GenericRepository.class, GenericRepositoryImpl.class);
	}

	public static RepositoryConstructor getRepositoryConstructor(Field field, Class<?> classImpl) {
		// check the target field is an interface
		Class<?> fieldInterface = field.getType();
		Assert.isTrue(fieldInterface.isInterface(), "Field target must be an interface");

		// get constructor's argument
		ResolvableType resolvableType = ResolvableType.forField(field);
		ResolvableType[] resolvableTypes = resolvableType.getGenerics();
		Class<?> argument = null;
		if (resolvableTypes.length != 0) {
			argument = resolvableTypes[0].getRawClass();
		}

		// get constructor
		Constructor<?> constructor = null;
		try {
			Class<?> clazzImpl = classImpl.equals(Class.class) ? repositories.get(fieldInterface) : classImpl;
			constructor = argument != null ? clazzImpl.getDeclaredConstructor(Class.class) : clazzImpl.getDeclaredConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			ReflectionUtils.handleReflectionException(e);
		}
		
		// return repository constructor
		return new RepositoryConstructor(constructor, argument);
	}

	public static Repository getRepository(Class<?> classInterface, Class<?> argument) {
		// check the target field is an interface
		Assert.isTrue(classInterface.isInterface(), "target must be an interface");

		// create class upon interface
		Object object = null;
		Class<?> classImpl = repositories.get(classInterface);
		try {
			object = argument == null ? classImpl.newInstance() : classImpl.getDeclaredConstructor(Class.class).newInstance(argument);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}

		// autowire and init object
		return (Repository) ApplicationContextUtils.autowireInitBean(object);
	}

}
