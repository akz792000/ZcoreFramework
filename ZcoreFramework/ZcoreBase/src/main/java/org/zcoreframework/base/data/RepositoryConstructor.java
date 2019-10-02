package org.zcoreframework.base.data;

import java.lang.reflect.Constructor;

public class RepositoryConstructor {
	
	private Constructor<?> constructor;
	
	private Class<?> argument;
	
	public Constructor<?> getConstructor() {
		return constructor;
	}
	
	public void setConstructor(Constructor<?> constructor) {
		this.constructor = constructor;
	}
	
	public Class<?> getArgument() {
		return argument;
	}
	
	public void setArgument(Class<?> argument) {
		this.argument = argument;
	}
	
	public RepositoryConstructor() {
	}
	
	public RepositoryConstructor(Constructor<?> constructor, Class<?> argument) {
		this.constructor = constructor;
		this.argument = argument;
	}
	
}
