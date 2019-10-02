/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.core;

import java.lang.reflect.Field;

public class FieldOrder {
	
	private Field field;
	
	private Object value;
	
	private Integer order;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public FieldOrder(Field field, Object value, Integer order) {
		this.field = field;
		this.value = value;
		this.order = order;
	}

}
