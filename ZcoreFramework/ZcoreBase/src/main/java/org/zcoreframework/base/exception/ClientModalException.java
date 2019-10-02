/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.exception;

import org.zcoreframework.base.util.JsonUtils;

import java.util.List;

@SuppressWarnings("serial")
public class ClientModalException extends BaseException {

	public ClientModalException() {
		super();
	}

	public ClientModalException(String message) {
		super(message);
	}

	public ClientModalException(String message, Object[] args) {
		super(message, args);
	}	
					
	public static void deliver(String message) throws ClientModalException {
		throw new ClientModalException(message);
	}
	
	public static void deliver(String message, Object[] args) throws ClientModalException {
		throw new ClientModalException(message, args);
	}	
	
	public static void deliverFromJson(String message) throws ClientModalException {
		List<Object> res = JsonUtils.toList(message);
		Object error = res.get(1);
		if (error instanceof List) {
			@SuppressWarnings("unchecked")
			List<Object> lst1 = (List<Object>) error;
			error = lst1.remove(0);
			throw new ClientModalException((String) error, lst1.toArray());
		} else {
			throw new ClientModalException((String) error);
		} 
	}
	
}