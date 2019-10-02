package org.zcoreframework.base.gateway.ftp.exception;

import org.zcoreframework.base.gateway.exception.GatewayException;

/**
 *
 */
public class ServerInfoNotFoundException extends GatewayException {

    public ServerInfoNotFoundException(String message) {
        super(message);
    }
    public ServerInfoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
