package org.zcoreframework.base.gateway.exception;

import java.util.Arrays;

/**
 *
 */
public class GatewayParseException extends GatewayException {

    public GatewayParseException(String message) {
        super(message);
    }

    public GatewayParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatewayParseException(String message, Long row, Object... args) {
        super(message, Arrays.asList(row, args));
    }

    public GatewayParseException(String message, Throwable cause, Long row, Object... args) {
        super(message, cause, Arrays.asList(row, args));
    }
}
