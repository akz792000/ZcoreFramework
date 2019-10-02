package org.zcoreframework.base.function;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.CallbackPreferringPlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Callback interface for transactional code. Used with {@link org.springframework.transaction.support.TransactionTemplate}'s
 * {@code execute} method, often as anonymous class within a method implementation.
 * <p>
 * <p>Typically used to assemble various calls to transaction-unaware data access
 * services into a higher-level service method with transaction demarcation. As an
 * alternative, consider the use of declarative transaction demarcation (e.g. through
 * Spring's {@link org.springframework.transaction.annotation.Transactional} annotation).
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/27/17
 * @see org.springframework.transaction.support.TransactionTemplate
 * @see org.springframework.transaction.support.CallbackPreferringPlatformTransactionManager
 */

@FunctionalInterface
public interface TransactionCallback<T> {

    /**
     * Gets called by {@link TransactionTemplate#execute} within a transactional context.
     * Does not need to care about transactions itself, although it can retrieve and
     * influence the status of the current transaction.
     * <p>
     * <p>Allows for returning a result object created within the transaction, i.e. a
     * domain object or a collection of domain objects. A RuntimeException thrown by the
     * callback is treated as application exception that enforces a rollback. Any such
     * exception will be propagated to the caller of the template, unless there is a
     * problem rolling back, in which case a Exception will be thrown.
     *
     * @param status associated transaction status
     * @return a result object, or {@code null}
     * @see TransactionTemplate#execute
     * @see CallbackPreferringPlatformTransactionManager#execute
     */
    T doInTransaction(TransactionStatus status) throws Exception;

}
