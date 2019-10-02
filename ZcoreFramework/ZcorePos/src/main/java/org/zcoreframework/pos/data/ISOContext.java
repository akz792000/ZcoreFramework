package org.zcoreframework.pos.data;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.zcoreframework.pos.domain.ISOTransactionEntity;

/**
 *
 */
public class ISOContext {

    private ISOSource isoSource;

    private ISOMsg msg;

    private ISOTransactionEntity tx;

    public ISOSource getIsoSource() {
        return isoSource;
    }

    public void setIsoSource(ISOSource isoSource) {
        this.isoSource = isoSource;
    }

    public ISOMsg getMsg() {
        return msg;
    }

    public void setMsg(ISOMsg msg) {
        this.msg = msg;
    }

    public ISOTransactionEntity getTx() {
        return tx;
    }

    public void setTx(ISOTransactionEntity tx) {
        this.tx = tx;
    }
}
