package org.zcoreframework.security.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.zcoreframework.base.util.JsonUtils;

@SuppressWarnings("serial")
public class FailedTryException extends AuthenticationException {

	/**
	 * Constructs a <code>FailedTryException</code> with the specified message.
	 *
	 * @param msg the detail message.
	 */
	public FailedTryException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructs a {@code FailedTryException} with the specified message and root
	 * cause.
	 *
	 * @param msg the detail message.
	 * @param t root cause
	 */
	public FailedTryException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public static void deliver(String msg, Object[] args) throws FailedTryException {
		Map<String, Object> map = new HashMap<>();
		map.put("msg", msg);
		map.put("args", args);
		throw new FailedTryException(JsonUtils.encode(map));
	}			
	
}