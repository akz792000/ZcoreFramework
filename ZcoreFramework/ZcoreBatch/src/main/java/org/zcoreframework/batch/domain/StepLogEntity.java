package org.zcoreframework.batch.domain;

import org.zcoreframework.base.util.ConstantsUtils.Schema;
import org.zcoreframework.batch.model.StepExecutionStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by z.azadi on 4/30/2016.
 */
@Entity
@Table(name = StepLogEntity.TABLE_NAME, schema = Schema.BH)
@NamedNativeQueries(
        @NamedNativeQuery(name = "StepLogEntity.loadStepComplete", query = "select max(s.id) from BH.BATCH_STEP_LOG s where s.SKIP_COUNT=0 and s.STATUS=0 and s.STEP_NAME=?1 ")
)
public class StepLogEntity {

    public static final String TABLE_NAME = "BATCH_STEP_LOG";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_SEQ";
    public static final String SEQUENCE_GENERATOR_NAME = TABLE_NAME + "_SEQUENCE";

    @Column(name = "ID")
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, schema = Schema.BH, sequenceName = SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @Id
    private Long id;

    @Column(name = "JOB_EXECUTION_ID")
    private Long jobExecutionId;

    @Column(name = "STEP_NAME")
    private String stepName;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "EXECUTION_TIME")
    private Long executionTime;

    @Column(name = "STATUS")
    private StepExecutionStatus status;

    @Column(name = "EXCEPTION_BODY")
    @Lob
    private String exceptionBody;

    @OneToMany(mappedBy = "stepLogEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<StepItemsLogEntity> stepDetailsLogEntities;


    @Column(name = "JOB_NAME")
    private String jobName;

    @Column(name = "COMMIT_COUNT")
    private int commitCount;

    @Column(name = "READ_COUNT")
    private int readCount;

    @Column(name = "FILTER_COUNT")
    private int filterCount;

    @Column(name = "WRITE_COUNT")
    private int writeCount;

    @Column(name = "SKIP_COUNT")
    private int skipCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobExecutionId() {
        return jobExecutionId;
    }

    public void setJobExecutionId(Long jobExecutionId) {
        this.jobExecutionId = jobExecutionId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public StepExecutionStatus getStatus() {
        return status;
    }

    public void setStatus(StepExecutionStatus status) {
        this.status = status;
    }


    public String getExceptionBody() {
        return exceptionBody;
    }

    public void setExceptionBody(String exceptionBody) {
        this.exceptionBody = exceptionBody;
    }

    public Set<StepItemsLogEntity> getStepDetailsLogEntities() {
        return stepDetailsLogEntities;
    }

    public void setStepDetailsLogEntities(Set<StepItemsLogEntity> stepDetailsLogEntities) {
        this.stepDetailsLogEntities = stepDetailsLogEntities;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(int commitCount) {
        this.commitCount = commitCount;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getFilterCount() {
        return filterCount;
    }

    public void setFilterCount(int filterCount) {
        this.filterCount = filterCount;
    }

    public int getWriteCount() {
        return writeCount;
    }

    public void setWriteCount(int writeCount) {
        this.writeCount = writeCount;
    }

    public int getSkipCount() {
        return skipCount;
    }

    public void setSkipCount(int skipCount) {
        this.skipCount = skipCount;
    }
}
