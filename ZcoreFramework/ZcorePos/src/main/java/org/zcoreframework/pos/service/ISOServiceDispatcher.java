package org.zcoreframework.pos.service;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.zcoreframework.base.beans.factory.InitializeAware;

/**
 *
 */
public interface ISOServiceDispatcher extends InitializeAware {

    void dispatch(ISOSource isoSource, ISOMsg isoMsg);
}
