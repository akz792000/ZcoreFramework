package org.zcoreframework.pos.service;

import org.jpos.iso.ISOMsg;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.pos.domain.ISOMessageEntity;
import org.zcoreframework.pos.domain.ISOTransactionEntity;

/**
 *
 */
public interface ISOTransactionService extends InitializeAware {

    ISOTransactionEntity findById(Long id);

    ISOTransactionEntity submit(ISOMsg msg);

    ISOMessageEntity finish(ISOMsg msg, ISOTransactionEntity txEntity);

    ISOMessageEntity finish(ISOMsg msg, ISOTransactionEntity txEntity, boolean deliver);

    ISOMessageEntity deliver(ISOMessageEntity msgEntity);
}
