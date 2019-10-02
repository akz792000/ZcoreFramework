package org.zcoreframework.pos.service;

import org.jpos.iso.ISOMsg;
import org.zcoreframework.base.annotation.Business;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.pos.annotation.ISOMethod;
import org.zcoreframework.pos.annotation.ISOService;

/**
 *
 */
@ISOService(mti = "1100")
@Business("PS_ISORegisterService")
public class ISORegisterService implements InitializeAware {

    @ISOMethod(prCodes = {"100000"})
    public void invoke(ISOMsg message) {

        message.set(39, "000");
    }

}
