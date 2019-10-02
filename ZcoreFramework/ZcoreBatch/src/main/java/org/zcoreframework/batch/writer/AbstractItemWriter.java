package org.zcoreframework.batch.writer;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.util.StopWatch;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.function.TransactionCallbackWithoutResult;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.zcoreframework.batch.util.TransactionUtils.executeTransactionally;

/**
 * Created by z.azadi on 4/10/2016.
 *
 * @author Zahra Azadi
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public abstract class AbstractItemWriter<T> implements ItemWriter<T>, InitializeAware {

    private static final Logger LOGGER = Logger.getLogger(AbstractItemWriter.class);

    private StepExecution stepExecution;

    @RepositoryInstance
    protected DefaultRepository repository;

    @Override
    public final void write(List<? extends T> items) throws Exception {
        try {
            LOGGER.info(format("Start to write in step name %s with status %s ", stepExecution.getStepName(), this.stepExecution.getStatus()));
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            writeTransactionally(items);
            stopWatch.stop();
            LOGGER.info(format("Writing is complete and takes %d ms", stopWatch.getLastTaskTimeMillis()));
        } catch (Throwable t) {
            LOGGER.error(format("An exception occurred in writer of %s : ", stepExecution.getStepName()), t);
            logErrorTransactionally(items, t);
        }
    }

    private void writeTransactionally(List<? extends T> items) throws Throwable {
        executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> doWrite(items));
    }

    protected abstract void doWrite(List<? extends T> items) throws Exception;

    private void logErrorTransactionally(List<? extends T> items, Throwable t) throws Exception {
        try {
            executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> logError(items, t));
        } catch (Exception e) {
            LOGGER.error(format("Fatal error : An exception occurred in logging error for writer of %s -> ", stepExecution.getStepName()), e);
            throw e;
        }
    }

    /**
     * If you want to write your business log for any exception that occurred in writer, you can override this method.
     */
    protected void logError(List<? extends T> items, Throwable e) throws Exception {
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
