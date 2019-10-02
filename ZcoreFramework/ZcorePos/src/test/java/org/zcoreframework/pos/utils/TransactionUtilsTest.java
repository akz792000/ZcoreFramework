package org.zcoreframework.pos.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.pos.domain.ISOTestEntity;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/testContext.xml"})
public class TransactionUtilsTest implements InitializeAware {

    @Resource
    private EntityManagerFactory factory;

    @RepositoryInstance
    private DefaultRepository repository;

    @Before
    public void tearDown() throws Exception {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        em.createQuery("delete from ISOTestEntity e").executeUpdate();
        tx.commit();
    }

    private List<ISOTestEntity> selectAll() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        List<ISOTestEntity> list = em.createQuery("select e from ISOTestEntity e", ISOTestEntity.class).getResultList();
        tx.commit();

        return list;
    }

    @Test
    public void runNormal() throws Exception {
        ISOTestEntity testEntity = TransactionUtils.runAtomically(status -> {
            ISOTestEntity entity = new ISOTestEntity();
            entity.setCode("100");
            entity.setContent("100");

            repository.persist(entity);
            return entity;
        });

        assertNotNull(testEntity.getId());
        assertEquals(1, selectAll().size());
    }

    @Test
    public void runSequence() throws Exception {
        TransactionUtils.runAtomically(status -> {
            ISOTestEntity entity = new ISOTestEntity();
            entity.setCode("100");
            entity.setContent("100");

            repository.persist(entity);
            return entity;
        });

        TransactionUtils.runAtomically(status -> {
            ISOTestEntity entity = new ISOTestEntity();
            entity.setCode("100");
            entity.setContent("100");

            repository.persist(entity);
            return entity;
        });


        assertEquals(2, selectAll().size());
    }

    @Test
    public void runRollback() throws Exception {
        ISOTestEntity testEntity = TransactionUtils.runAtomically(status -> {
            ISOTestEntity entity = new ISOTestEntity();
            entity.setCode("100");
            entity.setContent("100");

            repository.persist(entity);
            status.setRollbackOnly();
            return entity;
        });

        assertEquals(0, selectAll().size());
    }

    @Test
    public void runMultiLevel1() throws Exception {
        TransactionUtils.runAtomically(status -> {
            ISOTestEntity entity = new ISOTestEntity();
            entity.setCode("100");
            entity.setContent("100");

            repository.persist(entity);

            TransactionUtils.runAtomically(newStatus -> {
                ISOTestEntity entity2 = new ISOTestEntity();
                entity2.setCode("100");
                entity2.setContent("100");
                repository.persist(entity2);

                return null;
            });

            return entity;
        });

        assertEquals(2, selectAll().size());
    }

    @Test
    public void runMultiLevel2() throws Exception {
        ISOTestEntity testEntity = TransactionUtils.runAtomically(status -> {
            ISOTestEntity entity = new ISOTestEntity();
            entity.setCode("100");
            entity.setContent("100");

            repository.persist(entity);
            repository.flush();

            return TransactionUtils.runAtomically(newStatus -> {
                return repository.loadById(ISOTestEntity.class, entity.getId());
            });
        });

        assertNull(testEntity);
    }

    @Test
    public void runMultiLevel3() throws Exception {
        TransactionUtils.runAtomically(status -> {
            ISOTestEntity entity = new ISOTestEntity();
            entity.setCode("100");
            entity.setContent("100");

            repository.persist(entity);

            try {
                TransactionUtils.runAtomically(newStatus -> {
                    ISOTestEntity entity2 = new ISOTestEntity();
                    entity2.setCode("100");
                    entity2.setContent("100");
                    repository.persist(entity2);

                    throw new RuntimeException();
                });
            } catch (Exception e) {
                //Do Nothing
            }

            return entity;
        });

        assertEquals(1, selectAll().size());
    }

    @Test(expected = OptimisticLockException.class)
    public void runMultiLevelOptimistic() throws Exception {
        TransactionUtils.runAtomically(status -> {
            ISOTestEntity entity = new ISOTestEntity();
            entity.setCode("100");
            entity.setContent("100");

            repository.persist(entity);
            repository.flush();

            TransactionUtils.runAtomically(newStatus -> {
                entity.setCode("200");
                repository.merge(entity);

                return null;
            });

            return entity;
        });
    }

}