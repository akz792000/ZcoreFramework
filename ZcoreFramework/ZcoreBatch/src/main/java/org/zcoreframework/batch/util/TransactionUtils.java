package org.zcoreframework.batch.util;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.zcoreframework.base.exception.TransactionException;
import org.zcoreframework.base.function.TransactionCallback;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.Savepoint;

import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.zcoreframework.batch.util.EntityManagerUtils.getActiveConnection;
import static org.zcoreframework.batch.util.EntityManagerUtils.getEntityManager;
import static org.zcoreframework.batch.util.TransactionManagerUtils.getTransactionManager;
import static org.zcoreframework.batch.util.TransactionManagerUtils.getTransactionTemplate;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */
public class TransactionUtils {

    public static <T> T executeReadOnly(TransactionCallback<T> action) throws Exception {
        TransactionTemplate transactionTemplate = getTransactionTemplate();
        transactionTemplate.setReadOnly(true);

        Object result = transactionTemplate.execute(status -> {
            try {
                return action.doInTransaction(status);
            } catch (Throwable e) {
                return e;
            } finally {
                status.setRollbackOnly();
            }
        });

        if ((result != null) && (result instanceof Throwable)) {
            throw new TransactionException((Throwable) result);
        }
        return (T) result;
    }

    public static <T> T executeTransactionally(TransactionCallback<T> action) throws Exception {
        return executeTransactionally(REQUIRES_NEW, action);
    }

    public static <T> T executeTransactionally(Propagation propagation, TransactionCallback<T> action) throws Exception {
        // if (propagation == NESTED) return executeNestedTransaction(action);

        TransactionTemplate transactionTemplate = getTransactionTemplate();
        transactionTemplate.setPropagationBehavior(propagation.value());

        Object result = transactionTemplate.execute(status -> {
            try {
                return action.doInTransaction(status);
            } catch (Throwable e) {
                status.setRollbackOnly();
                return e;
            }
        });

        if ((result != null) && (result instanceof Throwable)) {
            throw new TransactionException((Throwable) result);
        }
        return (T) result;
    }

    /**
     * <B>Note: </B>Nested transaction currently not supported with JpaTransactionManager
     * <B>Note: </B>If propagation set as NESTED, you must be careful that the same object can not be used in two sequential nested transaction.
     *
     * @throws org.springframework.orm.jpa.JpaOptimisticLockingFailureException If one object is used in two sequential nested transaction.
     */
    private static <T> T executeNestedTransaction(TransactionCallback<T> action) throws Exception {
        TransactionStatus transaction = getTransactionManager().getTransaction(new DefaultTransactionDefinition(PROPAGATION_REQUIRED));

        EntityManager entityManager = getEntityManager();
        Connection connection = getActiveConnection(entityManager);
        if (connection == null) {
            throw new TransactionException("Can not get active connection!");
        }

        transaction.flush();
        Savepoint savepoint = connection.setSavepoint();
        try {
            T result = action.doInTransaction(transaction);
            transaction.flush();
            return result;
        } catch (Throwable t) {
            connection.rollback(savepoint);
            entityManager.clear();
            throw new TransactionException(t);
        }
    }
}
