package org.zcoreframework.batch.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.Map;

/**
 * Created by z.azadi on 7/11/2016.
 */
public class TaskScheduler extends QuartzJobBean {
    private String jobName;
    private JobLauncher jobLauncher;
    private JobLocator jobLocator;

    public JobLauncher getJobLauncher() {
        return jobLauncher;
    }

    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public JobLocator getJobLocator() {
        return jobLocator;
    }

    public void setJobLocator(JobLocator jobLocator) {
        this.jobLocator = jobLocator;
    }

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        @SuppressWarnings("unchecked")
        Map mapData = context.getMergedJobDataMap();

        jobName = (String) mapData.get("jobName");
        System.out.println("jobName :: before " + jobName);

        try {
            JobParameters param = new JobParametersBuilder()
                    .addDate("today", new Date()).
                            toJobParameters();
            JobExecution execution = jobLauncher.run(jobLocator.getJob(jobName), new JobParameters());
            System.out.println("Execution Status: " + execution.getStatus());
            System.out.println(": " + execution.getJobId());
        } catch (Exception e) {
            System.out.println("Encountered job execution exception! ");
            e.printStackTrace();
        }
    }
}

