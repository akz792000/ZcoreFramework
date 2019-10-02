package org.zcoreframework.batch.partitioner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.util.StopWatch;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.function.TransactionCallbackWithoutResult;
import org.zcoreframework.batch.exceptions.BatchRuntimeException;

import java.util.Map;

import static java.lang.String.format;
import static java.util.Collections.emptyMap;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.zcoreframework.batch.util.TransactionUtils.executeTransactionally;

/**
 * Created by z.azadi on 4/18/2016.
 *
 * @author Zahra Azadi
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public abstract class AbstractPartitioner implements Partitioner, InitializeAware {

    private static final Log LOGGER = LogFactory.getLog(AbstractPartitioner.class);

    @Override
    public final Map<String, ExecutionContext> partition(int gridSize) {
        try {
            LOGGER.info("Partitioner started ...");
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            final Map<String, ExecutionContext> partitionMap = doPartition(gridSize);
            stopWatch.stop();
            LOGGER.info(format("Partitioning is complete and takes %d ms", stopWatch.getLastTaskTimeMillis()));
            return partitionMap;
        } catch (Throwable t) {
            LOGGER.error("An exception occurred in partitioner : ", t);
            logErrorTransactionally(gridSize, t);
            return emptyMap();
        }
    }

    protected abstract Map<String, ExecutionContext> doPartition(int gridSize) throws Exception;

    private void logErrorTransactionally(int gridSize, Throwable t) {
        try {
            executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> logError(gridSize, t));
        } catch (Exception e) {
            final String errorMessage = "Fatal error : An exception occurred in logging error for partitioner -> ";
            LOGGER.error(errorMessage, e);
            throw new BatchRuntimeException(errorMessage, e);
        }
    }

    /**
     * If you want to write your business log for any exception that occurred in partitioner, you can override this method.
     */
    protected void logError(int gridSize, Throwable e) throws Exception {
        // There is not any default implementation.
    }
}
