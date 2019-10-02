/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.component;

import java.util.HashMap;
import java.util.Map;

public class ResponseResult {
	
	public enum ResponseType { JSON, PASSIVE, BYTE, DOWNLOAD }

	private ResponseType responseType = ResponseType.JSON;

	private Map<String, Object> parameter = new HashMap<>();

	private Object value;

	public ResponseType getResponseType() {
		return responseType;
	}

	public Map<String, Object> getParameter() {
		return parameter;
	}
	
	public void setParameter(Map<String, Object> parameter) {
		this.parameter = parameter;
	}

	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ResponseResult() {
	}

	public ResponseResult(Object value) {
		this.value = value;
	}

	public ResponseResult(ResponseType responseType, Object value) {
		this.responseType = responseType;
		this.value = value;
	}

}