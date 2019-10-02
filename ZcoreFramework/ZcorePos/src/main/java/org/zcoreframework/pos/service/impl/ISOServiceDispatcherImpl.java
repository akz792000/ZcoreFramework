package org.zcoreframework.pos.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.springframework.scheduling.annotation.Async;
import org.zcoreframework.pos.constant.ActionCodes;
import org.zcoreframework.pos.constant.TransactionStatus;
import org.zcoreframework.pos.data.ISOContext;
import org.zcoreframework.pos.data.ISOMessage;
import org.zcoreframework.pos.data.MethodMetadata;
import org.zcoreframework.pos.domain.ISOMessageEntity;
import org.zcoreframework.pos.domain.ISOTransactionEntity;
import org.zcoreframework.pos.exception.PosMessageFormatException;
import org.zcoreframework.pos.exception.PosRuntimeException;
import org.zcoreframework.pos.exception.PosSocketException;
import org.zcoreframework.pos.exception.PosStandardException;
import org.zcoreframework.pos.service.ISOServiceDispatcher;
import org.zcoreframework.pos.service.ISOServiceRegistry;
import org.zcoreframework.pos.service.ISOTransactionService;
import org.zcoreframework.pos.utils.InvokerUtils;
import org.zcoreframework.pos.utils.TransactionUtils;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 */
public class ISOServiceDispatcherImpl implements ISOServiceDispatcher {
    private final Log logger = LogFactory.getLog(ISOServiceDispatcherImpl.class);

    private ISOServiceRegistry registry;

    private ISOTransactionService txService;

    @Async
    public void dispatch(ISOSource isoSource, ISOMsg isoMsg) {
        ISOContext context = new ISOContext();
        context.setMsg(isoMsg);
        context.setIsoSource(isoSource);

        try {
            context.setTx(submitTransaction(isoMsg));

            if (TransactionStatus.ABT.equals(context.getTx().getStatus())) {
                redirect(context);
            } else {
                process(context);
            }

        } catch (Throwable e) {
            fallback(context, e);
        }
    }

    private ISOTransactionEntity submitTransaction(ISOMsg isoMsg) {
        return TransactionUtils.runAtomically(status -> txService.submit(isoMsg));
    }

    private void redirect(ISOContext context) {
        TransactionUtils.runAtomically(status -> {
            try {

                txService.finish(context.getMsg(), context.getTx(), true);
                context.getIsoSource().send(context.getMsg());

                return null;
            } catch (ISOException e) {
                throw new PosMessageFormatException(e);
            } catch (IOException e) {
                throw new PosSocketException(e);
            }
        });
    }

    private void process(ISOContext context) {
        TransactionUtils.runAtomically(status -> {
            try {
                Object bean = registry.getBean(context.getMsg().getMTI());
                MethodMetadata metadata = registry.getMethodMetadata(context.getMsg().getMTI(), context.getMsg().getString(3));

                context.setMsg(InvokerUtils.invoke(bean, metadata, context.getMsg()));

                txService.finish(context.getMsg(), context.getTx(), true);

                context.getIsoSource().send(context.getMsg());
            } catch (ISOException e) {
                throw new PosMessageFormatException(e);
            } catch (IOException e) {
                throw new PosSocketException(e);
            }

            return null;
        });
    }

    private void fallback(ISOContext context, Throwable ex) {
//        e.printStackTrace();
        logger.error(ex);

        if (ex instanceof PosStandardException) {
            context.getMsg().set(ISOMessage.ACTION_CODE, ((PosStandardException) ex).getStandardCode());
        } else if (ex instanceof PersistenceException || ex.getCause() instanceof PersistenceException
                || ex instanceof SQLException || ex.getCause() instanceof SQLException) {
            context.getMsg().set(ISOMessage.ACTION_CODE, ActionCodes.ER_DATABASE);
        } else {
            context.getMsg().set(ISOMessage.ACTION_CODE, ActionCodes.ER_INTERNAL_SYS);
        }

        try {
            ISOMessageEntity msgEntity = TransactionUtils.runAtomically(status -> {
                if (context.getTx() == null) {
                    return null;
                }

                context.setTx(txService.findById(context.getTx().getId()));
                context.getTx().setErrorComment(ex);

                return txService.finish(context.getMsg(), context.getTx());
            });

            context.getIsoSource().send(context.getMsg());

            TransactionUtils.runAtomically(status -> {
                txService.deliver(msgEntity);
                return null;
            });

        } catch (IOException | ISOException e) {
            throw new PosRuntimeException(e);
        }

    }

    public void setRegistry(ISOServiceRegistry registry) {
        this.registry = registry;
    }

    public void setTxService(ISOTransactionService txService) {
        this.txService = txService;
    }
}
