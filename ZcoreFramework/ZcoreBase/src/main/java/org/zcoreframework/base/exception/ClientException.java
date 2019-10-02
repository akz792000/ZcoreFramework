/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.exception;

import java.util.List;

import org.zcoreframework.base.util.JsonUtils;

@SuppressWarnings("serial")
public class ClientException extends BaseException {
		
	public ClientException() {
		super();
	}

	public ClientException(String message) {		
		super(message);	
	}	
	
	public ClientException(String message, Object[] args) {		
		super(message, args);
	}	
					
	public static void deliver(String message) throws ClientException {
		throw new ClientException(message);
	}
	
	public static void deliver(String message, Object[] args) throws ClientException {
		throw new ClientException(message, args);
	}	
	
	public static void deliverFromJson(String message) throws ClientException {
		List<Object> res = JsonUtils.toList(message);
		Object error = res.get(1);
		if (error instanceof List) {
			@SuppressWarnings("unchecked")
			List<Object> lst1 = (List<Object>) error;
			error = lst1.remove(0);
			throw new ClientException((String) error, lst1.toArray());
		} else {
			throw new ClientException((String) error);
		} 
	}
	
}