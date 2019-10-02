package org.zcoreframework.batch.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StopWatch;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.function.TransactionCallbackWithoutResult;
import org.zcoreframework.batch.exceptions.FilterException;
import org.zcoreframework.batch.exceptions.ShouldJobFailedException;

import static java.lang.Boolean.FALSE;
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
public abstract class AbstractItemProcessor<I, O> implements ItemProcessor<I, O>, InitializeAware {

    private static final Log LOGGER = LogFactory.getLog(AbstractItemProcessor.class);

    private static final String ITEM = "item";

    private StepExecution stepExecution;
    private Boolean shouldJobFailed = FALSE;

    @Override
    public final O process(I item) throws Exception {
        try {
            LOGGER.info(format("Start to process in Step Name %s", stepExecution.getStepName()));
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            final O result = executeReadOnly(status -> doProcess(item));
            stopWatch.stop();
            LOGGER.info(format("Processing is complete and takes %d ms", stopWatch.getLastTaskTimeMillis()));
            return result;
        } catch (Throwable t) {
            LOGGER.error(format("An exception occurred in processor of %s : ", stepExecution.getStepName()), t);
            if (shouldJobFailed) {
                LOGGER.error("Fatal Error : ", t);
                throw new ShouldJobFailedException(t).set(ITEM, item);
            }
            stepExecution.addFailureException(new FilterException(t).set(ITEM, item));
            logErrorTransactionally(item, t);
            return null;
        }
    }

    protected abstract O doProcess(I item) throws Exception;

    private void logErrorTransactionally(I item, Throwable t) throws Exception {
        try {
            executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> logError(item, t));
        } catch (Exception e) {
            LOGGER.error(format("Fatal error : An exception occurred in logging error for processor of %s -> ", stepExecution.getStepName()), e);
            throw e;
        }
    }

    /**
     * If you want to write your business log for any exception that occurred in processor, you can override this method.
     */
    protected void logError(I item, Throwable e) throws Exception {
        // There is not any default implementation.
    }

    public final Boolean getShouldJobFailed() {
        return shouldJobFailed;
    }

    public void setShouldJobFailed(Boolean shouldJobFailed) {
        this.shouldJobFailed = shouldJobFailed;
    }

    protected final StepExecution getStepExecution() {
        return stepExecution;
    }

    @BeforeStep
    protected final void setStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
}
