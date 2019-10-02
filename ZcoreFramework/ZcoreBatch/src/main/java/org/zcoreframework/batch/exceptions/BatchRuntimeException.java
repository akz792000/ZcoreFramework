package org.zcoreframework.batch.exceptions;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 5/8/17
 */

public class BatchRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BatchRuntimeException() {
    }

    public BatchRuntimeException(String message) {
        super(message);
    }

    public BatchRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BatchRuntimeException(Throwable cause) {
        super(cause);
    }

    public BatchRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
