/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.exception;

import org.springframework.security.core.AuthenticationException;

public class MemoryLockException extends AuthenticationException {

	private static final long serialVersionUID = 1440270459903904172L;

	/**
     * Constructs a <code>UsernameNotFoundException</code> with the specified
     * message.
     *
     * @param msg the detail message.
     */
    public MemoryLockException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code UsernameNotFoundException} with the specified message and root cause.
     *
     * @param msg the detail message.
     * @param t root cause
     */
    public MemoryLockException(String msg, Throwable t) {
        super(msg, t);
    }
}
