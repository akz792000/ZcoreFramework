package org.zcoreframework.base.gateway.exception;

import org.zcoreframework.base.exception.BaseException;

/**
 *
 */
public class GatewayException extends BaseException {

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayException(String message, Object... args) {
        super(message, args);
    }

    public GatewayException(String message, Throwable cause, Object... args) {
        super(message, args, cause);
    }

}
