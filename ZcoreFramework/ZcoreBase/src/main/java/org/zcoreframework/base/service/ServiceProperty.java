package org.zcoreframework.base.service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public interface ServiceProperty {

	String getName();

	String getTitle();

	List<ServiceProperty> getActions();

}
