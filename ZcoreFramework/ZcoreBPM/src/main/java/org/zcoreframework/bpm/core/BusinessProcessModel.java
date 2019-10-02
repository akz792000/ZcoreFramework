/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.bpm.core;

import java.util.HashMap;
import java.util.Map;

import org.zcoreframework.base.annotation.JsonAutoDetectNone;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonAutoDetectNone
@JsonTypeInfo(use = Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
public class BusinessProcessModel {

	@JsonProperty
	private String processDefinitionKey;
	
	@JsonProperty
	private String processInstanceId;
	
	@JsonProperty
	private String businessKey;
	
	@JsonProperty
	private String taskId;	

	@JsonProperty
	@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT)
	private Map<String, Object> variables = new HashMap<>();

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	
	public String getTaskId() {
		return taskId;
	}
	
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}	

	public Map<String, Object> getVariables() {
		return variables;
	}
	
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	
}
