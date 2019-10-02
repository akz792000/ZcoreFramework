package org.zcoreframework.batch.exceptions;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 5/7/17
 */

public class SkippableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SkippableException() {
    }

    public SkippableException(String message) {
        super(message);
    }

    public SkippableException(String message, Throwable cause) {
        super(message, cause);
    }

    public SkippableException(Throwable cause) {
        super(cause);
    }

    public SkippableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
