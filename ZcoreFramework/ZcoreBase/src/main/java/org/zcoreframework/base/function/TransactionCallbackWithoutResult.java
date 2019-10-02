package org.zcoreframework.base.function;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Simple convenience class for TransactionCallback implementation.
 * Allows for implementing a doInTransaction version without result,
 * i.e. without the need for a return statement.
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/27/17
 * @see TransactionTemplate
 */

@FunctionalInterface
public interface TransactionCallbackWithoutResult extends TransactionCallback<Object> {

    /**
     * Gets called by {@code TransactionTemplate.execute} within a transactional
     * context. Does not need to care about transactions itself, although it can retrieve
     * and influence the status of the current transaction.
     * <p>
     * <p>A RuntimeException thrown by the callback is treated as application
     * exception that enforces a rollback. An exception gets propagated to the
     * caller of the template.
     * <p>
     * <p>Note when using JTA: JTA transactions only work with transactional
     * JNDI resources, so implementations need to use such resources if they
     * want transaction support.
     *
     * @param status associated transaction status
     * @see TransactionTemplate#execute
     */
    void doInTransactionWithoutResult(TransactionStatus status) throws Exception;

    default Object doInTransaction(TransactionStatus status) throws Exception {
        doInTransactionWithoutResult(status);
        return null;
    }
}
