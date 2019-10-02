package org.zcoreframework.pos.exception;

import org.jpos.iso.ISOException;
import org.zcoreframework.pos.constant.ActionCodes;

/**
 *
 */
public class PosMessageFormatException extends PosStandardException {

    private final static long serialVersionUID = 1L;

    public PosMessageFormatException(ISOException cause) {
        super(ActionCodes.ER_MSG_FORMAT, cause);
    }
}
