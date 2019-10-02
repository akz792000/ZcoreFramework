package org.zcoreframework.batch.scheduler;


import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by z.azadi on 7/11/2016.
 */
public class SchedulerFactory {
    private Object jobLauncher;
    private Object jobLocator;


    public JobDetailFactoryBean create(String jobName) {
        JobDetailFactoryBean jobDetailBean = new JobDetailFactoryBean();
        jobDetailBean.setJobClass(TaskScheduler.class);
        Map<String, Object> map = new HashMap<>();
        map.put("jobLauncher", jobLauncher);
        map.put("jobLocator", jobLocator);
        map.put("jobName", jobName);
        jobDetailBean.setJobDataAsMap(map);
        return jobDetailBean;
    }


    public Object getJobLauncher() {
        return jobLauncher;
    }

    public void setJobLauncher(Object jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public Object getJobLocator() {
        return jobLocator;
    }

    public void setJobLocator(Object jobLocator) {
        this.jobLocator = jobLocator;
    }
}

