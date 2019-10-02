package org.zcoreframework.pos.service.impl;

import org.jpos.iso.ISOMsg;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.util.DateUtils;
import org.zcoreframework.pos.constant.ActionCodes;
import org.zcoreframework.pos.constant.DateFormats;
import org.zcoreframework.pos.constant.TransactionStatus;
import org.zcoreframework.pos.data.ISOMessage;
import org.zcoreframework.pos.domain.ISOMessageEntity;
import org.zcoreframework.pos.domain.ISOTransactionEntity;
import org.zcoreframework.pos.exception.PosStandardException;
import org.zcoreframework.pos.service.ISOTransactionService;
import org.zcoreframework.pos.utils.TransactionUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/testContext.xml"})
public class ISOTransactionServiceImplTest implements InitializeAware {

    @Autowired
    private ISOTransactionService transactionService;

    @Resource
    private EntityManagerFactory factory;

    @RepositoryInstance
    private DefaultRepository repository;

    @Before
    public void tearDown() throws Exception {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.createQuery("delete from ISOTransactionEntity e").executeUpdate();
        tx.commit();
    }

    @Test
    @Transactional
    public void submitNormal() throws Exception {
        ISOMsg msg = newISOMsg();
        ISOTransactionEntity r = transactionService.submit(msg);

        assertNotNull(r.getExpireDate());
        assertEquals(TransactionStatus.STR, r.getStatus());
        assertEquals(msg.getString(0), r.getIsoMti());
        assertEquals(msg.getString(11), r.getIsoTrace());
        assertEquals(msg.getString(12), r.getIsoTxDate());
        assertEquals(msg.getString(32), r.getIsoAcqCode());
        assertEquals(msg.getString(41), r.getIsoAccId());
    }

    @Test
    @Transactional
    public void submitMultipleDuplication() throws Exception {
        ISOMsg msg = newISOMsg();

        repository.persist(newTx());
        repository.persist(newTx());

        ISOTransactionEntity r = transactionService.submit(msg);

        assertEquals(TransactionStatus.ABT, r.getStatus());
        assertEquals(ActionCodes.ER_INTERNAL_SYS, msg.getString(ISOMessage.ACTION_CODE));
    }

    @Test
    @Transactional
    public void submitMultipleDone() throws Exception {
        ISOMsg msg = newISOMsg();
        ISOTransactionEntity tx = newTx();

        tx.setStatus(TransactionStatus.DNE);
        repository.persist(tx);

        ISOTransactionEntity r = transactionService.submit(msg);

        assertEquals(TransactionStatus.ABT, r.getStatus());
        assertEquals(ActionCodes.ER_REDUNDANT_TX, msg.getString(ISOMessage.ACTION_CODE));
    }

    @Test
    @Transactional
    public void submitMultipleRunning1() throws Exception {
        ISOMsg msg = newISOMsg();
        ISOTransactionEntity tx = newTx();

        tx.setExpireDate(DateUtils.format(new Date(new Date().getTime() + 10000L), DateFormats.COMPLETE));
        tx.setStatus(TransactionStatus.STR);
        repository.persist(tx);

        ISOTransactionEntity r = transactionService.submit(msg);

        assertEquals(TransactionStatus.ABT, r.getStatus());
        assertEquals(ActionCodes.ER_RUNNING_TX, msg.getString(ISOMessage.ACTION_CODE));
    }

    @Test
    @Transactional
    public void submitMultipleRunning2() throws Exception {
        ISOMsg msg = newISOMsg();
        ISOTransactionEntity tx = newTx();

        tx.setExpireDate("");
        tx.setStatus(TransactionStatus.STR);
        repository.persist(tx);

        ISOTransactionEntity r = transactionService.submit(msg);

        assertEquals(TransactionStatus.STR, r.getStatus());
        assertEquals(TransactionStatus.ABT, tx.getStatus());
        assertNotNull(tx.getComment());
    }

    @Test
    @Transactional
    public void submitInvalid() throws Exception {
        ISOMsg msg = newISOMsg();
        msg.unset(12);

        ISOTransactionEntity r = transactionService.submit(msg);

        assertEquals(TransactionStatus.ABT, r.getStatus());
        assertEquals(ActionCodes.ER_INVALID_PARAM, msg.getString(ISOMessage.ACTION_CODE));
    }

    @Test
    @Transactional
    public void finishNormal1() throws Exception {
        ISOMsg msg = newISOMsg();
        ISOTransactionEntity r = transactionService.submit(msg);

        ISOMessageEntity message = transactionService.finish(msg, r);

        assertEquals(TransactionStatus.DNE, r.getStatus());
        assertEquals(false, message.getIncome());
        assertEquals(false, message.getDelivered());
    }

    @Test
    @Transactional
    public void finishNormal2() throws Exception {
        ISOMsg msg = newISOMsg();
        ISOTransactionEntity r = transactionService.submit(msg);

        r.setStatus(TransactionStatus.ABT);
        ISOMessageEntity message = transactionService.finish(msg, r);

        assertEquals(TransactionStatus.ABT, r.getStatus());
        assertEquals(false, message.getIncome());
        assertEquals(false, message.getDelivered());
    }

    @Test(expected = PosStandardException.class)
    @Transactional
    public void finishInvalidStatus() throws Exception {
        ISOMsg msg = newISOMsg();
        ISOTransactionEntity r = transactionService.submit(msg);

        r.setStatus(TransactionStatus.DNE);
        transactionService.finish(msg, r);
    }

    @Test(expected = OptimisticLockException.class)
    public void finishOptimistic() throws Exception {
        ISOMsg msg = newISOMsg();

        ISOTransactionEntity r = TransactionUtils.runAtomically(status -> {
            return transactionService.submit(msg);
        });

        TransactionUtils.runAtomically(status -> {
            r.setComment("------");
            repository.merge(r);

            return null;
        });

        TransactionUtils.runAtomically(status -> {
            return transactionService.finish(msg, r);
        });
    }


    private ISOMsg newISOMsg() {
        ISOMsg msg = new ISOMsg("1100");

        msg.set(11, "666666");
        msg.set(12, "961209050114");
        msg.set(32, "1000");
        msg.set(41, "1000");

        return msg;
    }

    private ISOTransactionEntity newTx() {
        ISOMsg msg = newISOMsg();
        ISOTransactionEntity txEntity = new ISOTransactionEntity();

        txEntity.setStartDate(DateUtils.format(new Date(), DateFormats.COMPLETE));
        txEntity.setStatus(TransactionStatus.STR);
        txEntity.setIsoMti(msg.getString(0));
        txEntity.setIsoTrace(msg.getString(11));
        txEntity.setIsoTxDate(msg.getString(12));
        txEntity.setIsoAcqCode(msg.getString(32));
        txEntity.setIsoAccId(msg.getString(41));

        return txEntity;
    }

}