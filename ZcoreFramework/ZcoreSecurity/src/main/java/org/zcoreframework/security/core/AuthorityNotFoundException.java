package org.zcoreframework.security.core;

import org.springframework.security.core.AuthenticationException;

public class AuthorityNotFoundException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>UsernameNotFoundException</code> with the specified message.
	 *
	 * @param msg the detail message.
	 */
	public AuthorityNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a {@code UsernameNotFoundException} with the specified message and root
	 * cause.
	 *
	 * @param msg the detail message.
	 * @param t root cause
	 */
	public AuthorityNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}
