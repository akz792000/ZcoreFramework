/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.data;

public class CriteriaColumn {

	public enum OrderType {
		ASC, DESC, DEFAULT
	}

	private String name;

	private Class<?> type;

	private Object value;

	private OrderType orderType = OrderType.DEFAULT;

	public CriteriaColumn() {
	};

	public CriteriaColumn(String name, Class<?> type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
	};

	public CriteriaColumn(String name, OrderType orderType) {
		this.name = name;
		this.orderType = orderType;
	};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

}
