package org.zcoreframework.batch.listener;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.zcoreframework.base.function.TransactionCallbackWithoutResult;
import org.zcoreframework.batch.business.StepLogBusinessImpl;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.zcoreframework.batch.util.TransactionUtils.executeTransactionally;

/**
 * Created by z.azadi on 4/20/2016.
 */
public class CustomStepListener implements StepExecutionListener {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    private StepLogBusinessImpl business;

    public StepLogBusinessImpl getBusiness() {
        return business;
    }

    public void setBusiness(StepLogBusinessImpl business) {
        this.business = business;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        try {
            executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> business.persistStepLog(stepExecution));
        } catch (Exception e) {
            LOGGER.error(format("Fatal error : An exception occurred in CustomStepListener call after  step of %s -> ", stepExecution.getStepName()), e);
            e.printStackTrace();
        }
        return stepExecution.getExitStatus();
    }
}

