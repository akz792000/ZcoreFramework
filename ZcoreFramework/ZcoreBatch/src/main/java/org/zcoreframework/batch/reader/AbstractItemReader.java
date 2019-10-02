package org.zcoreframework.batch.reader;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.util.StopWatch;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.function.TransactionCallbackWithoutResult;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.zcoreframework.batch.util.TransactionUtils.executeReadOnly;
import static org.zcoreframework.batch.util.TransactionUtils.executeTransactionally;

/**
 * Created by z.azadi on 4/10/2016.
 *
 * @author Zahra Azadi
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public abstract class AbstractItemReader<T> implements ItemReader<T>, InitializeAware {

    private static final Logger LOGGER = Logger.getLogger(AbstractItemReader.class);

    private StepExecution stepExecution;

    public final T read() throws Exception {
        try {
            LOGGER.info(format("Start to read in step name %s with status %s ", stepExecution.getStepName(), this.stepExecution.getStatus()));
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            final T item = executeReadOnly(status -> doRead());
            stopWatch.stop();
            LOGGER.info(format("Reading is complete and takes %d ms", stopWatch.getLastTaskTimeMillis()));
            return item;
        } catch (Throwable t) {
            LOGGER.error(format("An exception occurred in reader of %s : ", stepExecution.getStepName()), t);
            logErrorTransactionally(t);
            return null;
        }
    }

    protected abstract T doRead() throws Exception;

    private void logErrorTransactionally(Throwable t) throws Exception {
        try {
            executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> logError(t));
        } catch (Exception e) {
            LOGGER.error(format("Fatal error : An exception occurred in logging error for reader of %s -> ", stepExecution.getStepName()), e);
            throw e;
        }
    }

    /**
     * If you want to write your business log for any exception that occurred in reader, you can override this method.
     */
    protected void logError(Throwable e) throws Exception {
        // There is not any default implementation.
    }

    protected final StepExecution getStepExecution() {
        return stepExecution;
    }

    @BeforeStep
    protected final void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
}
