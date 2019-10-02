package org.zcoreframework.batch.model;

/**
 * Created by z.azadi on 4/30/2016.
 */
public enum StepExecutionStatus {
    COMPLETED,
    STARTING,
    STARTED,
    STOPPING,
    STOPPED,
    FAILED,
    ABANDONED,
    UNKNOWN,
    SKIPPED;
}
