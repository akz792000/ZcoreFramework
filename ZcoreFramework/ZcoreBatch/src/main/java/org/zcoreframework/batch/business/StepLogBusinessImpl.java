package org.zcoreframework.batch.business;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.context.annotation.Scope;
import org.zcoreframework.base.annotation.Business;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.domain.StepItemsLogEntity;
import org.zcoreframework.batch.domain.StepLogEntity;
import org.zcoreframework.batch.exceptions.FilterException;
import org.zcoreframework.batch.exceptions.ShouldJobFailedException;
import org.zcoreframework.batch.model.StepExecutionStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by z.azadi on 5/1/2016.
 */
@Business
@Scope("prototype")
public class StepLogBusinessImpl implements InitializeAware {

    @RepositoryInstance
    private DefaultRepository defaultRepository;

    public void persistStepLog(StepExecution stepExecution) {

        StepLogEntity stepLogEntity = new StepLogEntity();
        stepLogEntity.setStepName(stepExecution.getStepName());
        stepLogEntity.setStartDate(stepExecution.getStartTime());
        stepLogEntity.setJobExecutionId(stepExecution.getJobExecutionId());
        stepLogEntity.setJobName(stepExecution.getJobExecution().getJobInstance().getJobName());
        stepLogEntity.setExecutionTime(System.currentTimeMillis() - stepExecution.getStartTime().getTime()); // diff Second stopwatch
        stepLogEntity.setExceptionBody(stepExecution.getFailureExceptions().stream().map(ExceptionUtils::getFullStackTrace).collect(Collectors.toSet()).toString());
        Set<StepItemsLogEntity> stepDetailsLogEntitySet = new HashSet<>();
        stepLogEntity.setReadCount(stepExecution.getReadCount());
        stepLogEntity.setCommitCount(stepExecution.getCommitCount());
        stepLogEntity.setWriteCount(stepExecution.getWriteCount());
        stepLogEntity.setFilterCount(stepExecution.getFilterCount());
        stepLogEntity.setSkipCount(stepExecution.getProcessSkipCount() + stepExecution.getReadSkipCount() + stepExecution.getWriteSkipCount());
        // set Status Step
        if (stepExecution.getFailureExceptions().isEmpty())
            stepLogEntity.setStatus(getStepExecutionStatus(stepExecution.getStatus()));
        else
            for (Throwable throwable : stepExecution.getFailureExceptions()) {
                if (throwable instanceof ShouldJobFailedException) {
                    stepLogEntity.setStatus(StepExecutionStatus.FAILED);
                    if (((ShouldJobFailedException) throwable).get("item") != null) {
                        StepItemsLogEntity stepDetailsLogEntity = new StepItemsLogEntity();
                        stepDetailsLogEntity.setStepLogEntity(stepLogEntity);
                        stepDetailsLogEntity.setItem(((ShouldJobFailedException) throwable).get("item").toString());
                        stepDetailsLogEntitySet.add(stepDetailsLogEntity);
                    }
                } else if (throwable instanceof FilterException) {
                    stepLogEntity.setStatus(StepExecutionStatus.SKIPPED);
                    if (((FilterException) throwable).get("item") != null) {
                        StepItemsLogEntity stepDetailsLogEntity = new StepItemsLogEntity();
                        stepDetailsLogEntity.setStepLogEntity(stepLogEntity);
                        stepDetailsLogEntity.setItem(((FilterException) throwable).get("item").toString());
                        stepDetailsLogEntitySet.add(stepDetailsLogEntity);
                    }
                }
            }
        stepLogEntity.setStepDetailsLogEntities(stepDetailsLogEntitySet);
        defaultRepository.persist(stepLogEntity);
    }

    private StepExecutionStatus getStepExecutionStatus(BatchStatus status) {
        StepExecutionStatus stepExecutionStatus = StepExecutionStatus.UNKNOWN;
        switch (status) {
            case STARTING:
                stepExecutionStatus = StepExecutionStatus.STARTING;
                break;
            case STARTED:
                stepExecutionStatus = StepExecutionStatus.STARTED;
                break;
            case STOPPING:
                stepExecutionStatus = StepExecutionStatus.STOPPING;
                break;
            case STOPPED:
                stepExecutionStatus = StepExecutionStatus.STOPPED;
                break;
            case FAILED:
                stepExecutionStatus = StepExecutionStatus.FAILED;
                break;
            case COMPLETED:
                stepExecutionStatus = StepExecutionStatus.COMPLETED;
                break;
            case ABANDONED:
                stepExecutionStatus = StepExecutionStatus.ABANDONED;
                break;
            case UNKNOWN:
                stepExecutionStatus = StepExecutionStatus.UNKNOWN;
                break;
        }
        return stepExecutionStatus;
    }
}