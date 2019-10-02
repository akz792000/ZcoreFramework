package org.zcoreframework.pos.data;

import org.zcoreframework.pos.constant.ActionCodes;

import java.util.HashMap;

/**
 * ZCore Message
 */
public class ISOMessage extends HashMap<Integer, Object> {

    private final static long serialVersionUID = 1L;

    public final static int ACTION_CODE = 39;

    public ISOMessage succeed() {
        this.put(ISOMessage.ACTION_CODE, ActionCodes.SUCCEED);
        return this;
    }

    public void result(String errorCode) {
        this.put(ISOMessage.ACTION_CODE, errorCode);
    }
}
