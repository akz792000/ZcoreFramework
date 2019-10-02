package org.zcoreframework.pos.service.impl;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.pos.constant.ActionCodes;
import org.zcoreframework.pos.constant.TransactionStatus;
import org.zcoreframework.pos.data.ISOMessage;
import org.zcoreframework.pos.domain.ISOTransactionEntity;
import org.zcoreframework.pos.exception.PosRuntimeException;
import org.zcoreframework.pos.service.ISOServiceDispatcher;
import org.zcoreframework.pos.service.ISOServiceRegistry;
import org.zcoreframework.pos.service.ISOTransactionService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/testContext.xml"})
public class ISOServiceDispatcherImplTest implements InitializeAware {

    @Resource
    private EntityManagerFactory factory;

    @Autowired
    private ISOServiceRegistry registry;

    @Autowired
    private ISOTransactionService txService;

    private ISOServiceDispatcherImpl dispatcher;

    @Before
    public void setUp() throws Exception {
        dispatcher = new ISOServiceDispatcherImpl();
        dispatcher.setRegistry(registry);
        dispatcher.setTxService(txService);

        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.createQuery("delete from ISOTransactionEntity e").executeUpdate();
        em.createQuery("delete from ISOTestEntity e").executeUpdate();
        tx.commit();
    }

    @Test
    public void dispatchNormal() throws Exception {
        ISOMsg msg = newISOMsg();

        dispatcher.dispatch(newISOSource(), msg);

        ISOTransactionEntity tx = loadTx();

        assertEquals(tx.getActionCode(), msg.getString(ISOMessage.ACTION_CODE));
        assertEquals(TransactionStatus.DNE, tx.getStatus());
        assertEquals("000", tx.getActionCode());
        assertNotNull(tx.getInMsgId());
        assertNotNull(tx.getOutMsgId());
    }

    @Test
    public void dispatchEmptyField() throws Exception {
        ISOMsg msg = newISOMsg();
        msg.unset(11);

        dispatcher.dispatch(newISOSource(), msg);

        ISOTransactionEntity tx = loadTx();

        assertEquals(tx.getActionCode(), msg.getString(ISOMessage.ACTION_CODE));
        assertEquals(TransactionStatus.ABT, tx.getStatus());
        assertEquals(ActionCodes.ER_INVALID_PARAM, tx.getActionCode());
        assertNotNull(tx.getInMsgId());
        assertNotNull(tx.getOutMsgId());
    }

    @Test
    public void dispatchWrongMti() throws Exception {
        ISOMsg msg = newISOMsg();
        msg.setMTI("1350");

        dispatcher.dispatch(newISOSource(), msg);

        ISOTransactionEntity tx = loadTx();

        assertEquals(tx.getActionCode(), msg.getString(ISOMessage.ACTION_CODE));
        assertEquals(TransactionStatus.DNE, tx.getStatus());
        assertEquals(ActionCodes.ER_INTERNAL_SYS, tx.getActionCode());
        assertNotNull(tx.getInMsgId());
        assertNotNull(tx.getOutMsgId());
    }

    @Test(expected = PosRuntimeException.class)
    public void dispatchConnectionFailed() throws Exception {
        dispatcher.dispatch(new ISOSource() {
            @Override
            public void send(ISOMsg isoMsg) throws IOException, ISOException {
                throw new IOException();
            }

            @Override
            public boolean isConnected() {
                return false;
            }
        }, newISOMsg());
    }

    @Test
    public void dispatchPersist() throws Exception {
        ISOMsg msg = newISOMsg();
        msg.set(3, "100001");

        dispatcher.dispatch(newISOSource(), msg);

        ISOTransactionEntity tx = loadTx();

        assertEquals(1L, countTest());
        assertEquals(tx.getActionCode(), msg.getString(ISOMessage.ACTION_CODE));
        assertEquals(TransactionStatus.DNE, tx.getStatus());
        assertEquals("000", tx.getActionCode());
        assertNotNull(tx.getInMsgId());
        assertNotNull(tx.getOutMsgId());
    }

    @Test
    public void dispatchPersistFailed() throws Exception {
        ISOMsg msg = newISOMsg();
        msg.set(3, "100002");

        dispatcher.dispatch(newISOSource(), msg);

        ISOTransactionEntity tx = loadTx();

        assertEquals(0L, countTest());
        assertEquals(tx.getActionCode(), msg.getString(ISOMessage.ACTION_CODE));
        assertEquals(TransactionStatus.DNE, tx.getStatus());
        assertEquals("909", tx.getActionCode());
        assertNotNull(tx.getInMsgId());
        assertNotNull(tx.getOutMsgId());
        assertEquals(true, tx.getComment().contains("RuntimeException"));
    }

    @Test
    public void dispatchPersistFallback() throws Exception {
        ISOMsg msg = newISOMsg();
        msg.set(3, "100003");

        dispatcher.dispatch(newISOSource(), msg);

        ISOTransactionEntity tx = loadTx();

        assertEquals(0L, countTest());
        assertEquals(tx.getActionCode(), msg.getString(ISOMessage.ACTION_CODE));
        assertEquals(TransactionStatus.DNE, tx.getStatus());
        assertEquals("101", tx.getActionCode());
        assertNotNull(tx.getInMsgId());
        assertNotNull(tx.getOutMsgId());
    }

    private long countTest() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        List list = em.createQuery("select count(e) from ISOTestEntity e").getResultList();
        tx.commit();

        if (list == null || list.size() != 1) {
            throw new RuntimeException();
        }

        return (Long) list.get(0);
    }

    private ISOTransactionEntity loadTx() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        List list = em.createQuery("select e from ISOTransactionEntity e").getResultList();
        tx.commit();

        if (list == null || list.size() != 1) {
            throw new RuntimeException();
        }

        return (ISOTransactionEntity) list.get(0);
    }

    private ISOSource newISOSource() {
        return new ISOSource() {
            @Override
            public void send(ISOMsg isoMsg) throws IOException, ISOException {
            }

            @Override
            public boolean isConnected() {
                return true;
            }
        };
    }

    private ISOMsg newISOMsg() {
        ISOMsg msg = new ISOMsg("1200");

        msg.set(3, "500000");
        msg.set(11, "666666");
        msg.set(12, "961209050114");
        msg.set(32, "1000");
        msg.set(41, "1000");

        return msg;
    }
}