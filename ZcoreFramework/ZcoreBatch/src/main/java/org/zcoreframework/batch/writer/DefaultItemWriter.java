package org.zcoreframework.batch.writer;

import org.apache.log4j.Logger;
import org.zcoreframework.base.function.TransactionCallbackWithoutResult;
import org.zcoreframework.batch.exceptions.BatchRuntimeException;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.NESTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.zcoreframework.batch.util.TransactionUtils.executeTransactionally;

/**
 * <B>Note: </B>This type of writer is not supported, because nested transaction currently not supported with JpaTransactionManager
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/22/17
 */
public abstract class DefaultItemWriter<T> extends AbstractItemWriter<T> {

    private static final Logger LOGGER = Logger.getLogger(DefaultItemWriter.class);

    @Override
    protected final void doWrite(List<? extends T> items) throws Exception {
        for (T item : items) {
            try {
                executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> doWrite(item));
            } catch (Throwable t) {
                LOGGER.error(format("An exception occurred in writer of %s : ", getStepExecution().getStepName()), t);
                logErrorTransactionally(item, t);
            }
        }
    }

    protected abstract void doWrite(T item) throws Exception;

    private void logErrorTransactionally(T item, Throwable t) throws Exception {
        executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> logError(item, t));
    }

    /**
     * If you want to write your business log for any exception that occurred in writer, you can override this method.
     */
    protected void logError(T item, Throwable e) throws Exception {
        // There is not any default implementation.
    }

    @Override
    protected final void logError(List<? extends T> items, Throwable e) throws Exception {
        final String errorMessage = format("Fatal error : An exception occurred in logging error for writer of %s -> ", getStepExecution().getStepName());
        LOGGER.error(errorMessage, e);
        throw new BatchRuntimeException(errorMessage, e);
    }
}
