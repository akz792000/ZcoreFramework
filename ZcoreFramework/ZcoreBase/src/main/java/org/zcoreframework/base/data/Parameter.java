package org.zcoreframework.base.data;

import javax.persistence.ParameterMode;

public class Parameter {

	private Class<?> type;
	
	private ParameterMode mode;
	
	private Object value;

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public ParameterMode getMode() {
		return mode;
	}

	public void setMode(ParameterMode mode) {
		this.mode = mode;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public Parameter() {

	}
	
	public Parameter(Class<?> type, ParameterMode mode) {
		this.type = type;
		this.mode = mode;
	}	
	
	public Parameter(Class<?> type, ParameterMode mode, Object value) {
		this.type = type;
		this.mode = mode;
		this.value = value;
	}
		
}
