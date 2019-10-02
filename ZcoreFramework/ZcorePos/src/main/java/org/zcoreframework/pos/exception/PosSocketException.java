package org.zcoreframework.pos.exception;

import org.zcoreframework.pos.constant.ActionCodes;

import java.io.IOException;

/**
 *
 */
public class PosSocketException extends PosSystemException {

    private final static long serialVersionUID = 1L;

    public PosSocketException(IOException cause) {
        super("Can not establish connection", cause);
    }
}
