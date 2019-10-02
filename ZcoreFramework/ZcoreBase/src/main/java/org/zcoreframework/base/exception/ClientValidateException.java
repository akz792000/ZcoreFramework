/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.zcoreframework.base.util.JsonUtils;

@SuppressWarnings("serial")
public class ClientValidateException extends BaseException {
	
	private String component;
	
	public String getComponent() {
		return component;
	}
	
	public void setComponent(String component) {
		this.component = component;
	}
		
	public ClientValidateException(String component) {
		super();
		this.component = component;
	}

	public ClientValidateException(String component, String message) {		
		super(message);
		this.component = component;
	}	
	
	public ClientValidateException(String component, String message, Object[] args) {		
		super(message, args);
		this.component = component;
	}	
					
	public static void deliver(String component, String message) throws ClientValidateException {
		throw new ClientValidateException(component, message);
	}
	
	public static void deliver(String component, String message, Object[] args) throws ClientValidateException {
		throw new ClientValidateException(component, message, args);
	}
	
	@SuppressWarnings("unchecked")
	public static void deliverFromJson(String message) throws ClientValidateException {
		List<Object> res = JsonUtils.toList(message);		
		Map<String, Object> error = (Map<String, Object>) res.get(1);
		Entry<String, Object> entry = error.entrySet().iterator().next();
		List<Object> items = (List<Object>) entry.getValue();
		Object expression = items.remove(0);
		throw new ClientValidateException(entry.getKey(), (String) expression, items.toArray());
	}	
	
	@Override
	public Object getMessageArgs() {
		Map<String, Object> map = new HashMap<>();
		Object object = super.getMessageArgs();		
		map.put(component, object instanceof String ? Arrays.asList(object) : object);
		return map;
	}
}
