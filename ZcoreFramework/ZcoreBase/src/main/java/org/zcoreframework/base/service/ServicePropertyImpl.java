package org.zcoreframework.base.service;

import java.io.Serializable;
import java.util.List;

import org.zcoreframework.base.annotation.JsonAutoDetectNone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonAutoDetectNone
public class ServicePropertyImpl implements ServiceProperty, Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String name;

	@JsonProperty
	private String title;

	@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT)
	@JsonProperty
	private List<ServiceProperty> actions;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public List<ServiceProperty> getActions() {
		return actions;
	}

	public void setActions(List<ServiceProperty> actions) {
		this.actions = actions;
	}
	
	public ServicePropertyImpl() {
		
	}

	public ServicePropertyImpl(String name, String title) {
		this.name = name;
		this.title = title;
	}

	public ServicePropertyImpl(String name, String title, List<ServiceProperty> actions) {
		this.name = name;
		this.title = title;
		this.actions = actions;
	}
	
	public ServicePropertyImpl(List<ServiceProperty> actions) {
		this.actions = actions;
	}	

}
