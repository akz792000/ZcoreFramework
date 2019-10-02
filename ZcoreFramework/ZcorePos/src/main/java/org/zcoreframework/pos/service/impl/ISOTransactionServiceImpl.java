package org.zcoreframework.pos.service.impl;

import org.jpos.iso.ISOMsg;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.util.ArrayList;
import org.zcoreframework.base.util.DateUtils;
import org.zcoreframework.pos.constant.ActionCodes;
import org.zcoreframework.pos.constant.DateFormats;
import org.zcoreframework.pos.constant.TransactionStatus;
import org.zcoreframework.pos.data.ISOMessage;
import org.zcoreframework.pos.domain.ISOMessageEntity;
import org.zcoreframework.pos.domain.ISOTransactionEntity;
import org.zcoreframework.pos.exception.PosStandardException;
import org.zcoreframework.pos.service.ISOTransactionService;
import org.zcoreframework.pos.utils.ISOMsgUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@SuppressWarnings("unchecked")
public class ISOTransactionServiceImpl implements ISOTransactionService {

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    public ISOTransactionEntity findById(Long id) {
        return repository.loadById(ISOTransactionEntity.class, id);
    }

    @Override
    public ISOTransactionEntity submit(ISOMsg msg) {
        String currentDate = DateUtils.format(new Date(), DateFormats.COMPLETE);

        ISOMessageEntity msgEntity = new ISOMessageEntity();
        msgEntity.setIncome(true);
        msgEntity.setDelivered(Boolean.TRUE);
        msgEntity.setContent(ISOMsgUtils.parse(msg));

        repository.persist(msgEntity);

        ISOTransactionEntity txEntity = new ISOTransactionEntity();

        txEntity.setInMsgId(msgEntity.getId());
        txEntity.setStartDate(currentDate);
        txEntity.setStatus(TransactionStatus.STR);
        txEntity.setExpireDate(DateUtils.format(
                new Date(new Date().getTime() + getExpireDuration()), DateFormats.COMPLETE));

        txEntity.setIsoMti(msg.getString(0));
        txEntity.setIsoTrace(msg.getString(11));
        txEntity.setIsoTxDate(msg.getString(12));
        txEntity.setIsoAcqCode(msg.getString(32));
        txEntity.setIsoAccId(msg.getString(41));

        if (!validate(txEntity)) {
            txEntity.setStatus(TransactionStatus.ABT);
            ISOMsgUtils.result(msg, ActionCodes.ER_INVALID_PARAM);

            repository.persist(txEntity);
            return txEntity;
        }

        List<ISOTransactionEntity> list = findSubmitted(txEntity);

        if (list.size() > 1) {
            ISOMsgUtils.result(msg, ActionCodes.ER_INTERNAL_SYS);
            txEntity.setStatus(TransactionStatus.ABT);
            txEntity.setComment("Multiple transaction are registered");

        } else if (!list.isEmpty() && TransactionStatus.DNE.equals(list.get(0).getStatus())) {
            ISOMsgUtils.result(msg, ActionCodes.ER_REDUNDANT_TX);
            txEntity.setStatus(TransactionStatus.ABT);

        } else if (!list.isEmpty() && TransactionStatus.STR.equals(list.get(0).getStatus())) {
            ISOTransactionEntity runningTx = list.get(0);

            if (runningTx.getExpireDate() == null || runningTx.getExpireDate().isEmpty()
                    || runningTx.getExpireDate().compareTo(txEntity.getStartDate()) < 0) {
                runningTx.setStatus(TransactionStatus.ABT);
                runningTx.setComment("Transaction is expired.");

                repository.merge(runningTx);
            } else {
                ISOMsgUtils.result(msg, ActionCodes.ER_RUNNING_TX);
                txEntity.setStatus(TransactionStatus.ABT);
            }
        }

        repository.persist(txEntity);

        return txEntity;
    }


    @Override
    public ISOMessageEntity finish(ISOMsg msg, ISOTransactionEntity txEntity) {
        return finish(msg, txEntity, false);
    }

    @Override
    public ISOMessageEntity finish(ISOMsg msg, ISOTransactionEntity txEntity, boolean deliver) {
        if (TransactionStatus.DNE.equals(txEntity.getStatus())) {
            throw new PosStandardException(ActionCodes.ER_REDUNDANT_TX);
        }

        ISOMsgUtils.response(msg);

        String endDate = DateUtils.format(new Date(), DateFormats.COMPLETE);

        ISOMessageEntity msgEntity = new ISOMessageEntity();
        msgEntity.setIncome(false);
        msgEntity.setDelivered(deliver);
        msgEntity.setContent(ISOMsgUtils.parse(msg));

        repository.persist(msgEntity);

        if (TransactionStatus.STR.equals(txEntity.getStatus())) {
            txEntity.setStatus(TransactionStatus.DNE);
        }

        txEntity.setActionCode(msg.getString(ISOMessage.ACTION_CODE));
        txEntity.setEndDate(endDate);
        txEntity.setOutMsgId(msgEntity.getId());

        repository.merge(txEntity);

        return msgEntity;
    }

    @Override
    public ISOMessageEntity deliver(ISOMessageEntity msgEntity) {
        if (msgEntity == null) {
            return null;
        }

        msgEntity.setDelivered(true);
        return repository.merge(msgEntity);
    }

    private boolean validate(ISOTransactionEntity entity) {
        if (entity.getIsoMti() == null || entity.getIsoTrace() == null || entity.getIsoTxDate() == null
                || entity.getIsoAccId() == null || entity.getIsoAcqCode() == null) {
            return false;
        }

        return true;
    }

    private List<ISOTransactionEntity> findSubmitted(ISOTransactionEntity txEntity) {
        Map<String, Object> params = new HashMap<>();
        params.put("isoMti", txEntity.getIsoMti());
        params.put("isoTrace", txEntity.getIsoTrace());
        params.put("isoTxDate", txEntity.getIsoTxDate());
        params.put("isoAcqCode", txEntity.getIsoAcqCode());
        params.put("isoAccId", txEntity.getIsoAccId());

        List<ISOTransactionEntity> list = (List<ISOTransactionEntity>) repository.loadResultList(
                ISOTransactionEntity.getSelectSubmitted(), params);

        if (list == null) {
            return new ArrayList<>();
        }

        return list.stream().filter(item -> !TransactionStatus.ABT.equals(item.getStatus()))
                .collect(Collectors.toList());
    }

    private long getExpireDuration() {
        String str = PropertiesFactoryBean.getProperties().getProperty("zcore.pos.server.expireDuration");

        return (str == null || str.isEmpty()) ? 5000 : Long.parseLong(str);
    }
}
