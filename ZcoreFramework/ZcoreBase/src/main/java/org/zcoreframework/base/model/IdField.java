package org.zcoreframework.base.model;

public class IdField<ID> {

	private String name;

	private ID value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ID getValue() {
		return value;
	}
	
	public void setValue(ID value) {
		this.value = value;
	}

	public IdField() {
	}

	public IdField(String name, ID value) {
		this.name = name;
		this.value = value;
	}

}
