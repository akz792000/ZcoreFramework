package org.zcoreframework.batch.tasklet;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.util.StopWatch;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.function.TransactionCallbackWithoutResult;
import org.zcoreframework.batch.exceptions.FilterException;
import org.zcoreframework.batch.exceptions.ShouldJobFailedException;
import org.zcoreframework.batch.util.TransactionUtils;

import static java.lang.Boolean.FALSE;
import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

/**
 * Created by z.azadi on 4/18/2016.
 *
 * @author Zahra Azadi
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */
public abstract class AbstractTasklet implements Tasklet, InitializeAware {

    private static final Logger LOGGER = Logger.getLogger(AbstractTasklet.class);

    private Boolean shouldJobFailed = FALSE;

    @Override
    public final RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        final StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        try {
            LOGGER.info(format("Start to execute the tasklet Name %s with status %s ", stepExecution.getStepName(), stepExecution.getStatus()));
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            executeTransactionally(stepContribution, chunkContext);
            stopWatch.stop();
            LOGGER.info(format("Tasklet is complete and takes %d ms", stopWatch.getLastTaskTimeMillis()));
        } catch (Throwable t) {
            LOGGER.error(format("An exception occurred in tasklet of %s : ", stepExecution.getStepName()), t);
            if (shouldJobFailed) {
                LOGGER.error("Fatal Error : ", t);
                throw new ShouldJobFailedException(t);
            }
            stepExecution.addFailureException(new FilterException(t));
            logErrorTransactionally(stepContribution, chunkContext, t);
        }
        return RepeatStatus.FINISHED;
    }

    private void executeTransactionally(StepContribution stepContribution, ChunkContext chunkContext) throws Throwable {
        TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> doExecute(stepContribution, chunkContext));
    }

    protected abstract void doExecute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception;

    private void logErrorTransactionally(StepContribution stepContribution, ChunkContext chunkContext, Throwable t) throws Exception {
        try {
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> logError(stepContribution, chunkContext, t));
        } catch (Exception e) {
            LOGGER.error(format("Fatal error : An exception occurred in logging error for tasklet of %s -> ", chunkContext.getStepContext().getStepName()), e);
            throw e;
        }
    }

    /**
     * If you want to write your business log for any exception that occurred in tasklet, you can override this method.
     */
    protected void logError(StepContribution stepContribution, ChunkContext chunkContext, Throwable e) throws Exception {
        // There is not any default implementation.
    }

    public final Boolean getShouldJobFailed() {
        return shouldJobFailed;
    }

    public void setShouldJobFailed(Boolean shouldJobFailed) {
        this.shouldJobFailed = shouldJobFailed;
    }
}