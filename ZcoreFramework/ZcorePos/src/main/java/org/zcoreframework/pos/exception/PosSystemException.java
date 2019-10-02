package org.zcoreframework.pos.exception;

import org.zcoreframework.pos.constant.ActionCodes;

/**
 *
 */
public class PosSystemException extends PosStandardException {

    private static final long serialVersionUID = 1L;

    public PosSystemException(String reason) {
        super(reason, ActionCodes.ER_INTERNAL_SYS);
    }

    public PosSystemException(String reason, Throwable e) {
        super(reason, ActionCodes.ER_INTERNAL_SYS, e);
    }
}
