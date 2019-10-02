package org.zcoreframework.batch.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;
import org.zcoreframework.base.beans.factory.InitializeAware;

import javax.annotation.Resource;
import java.util.Date;

import static org.zcoreframework.base.util.ApplicationContextUtils.getApplicationContext;


/**
 * Created by z.azadi on 5/1/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/testContext.xml"})
public class BatchTestUnit implements InitializeAware {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Autowired
    private JobLauncher jobLauncher;


/*    @Resource(name = "simpleJob")
    private Job simpleJob;*/

    @Resource(name = "simplePartition")
    private Job simplePartition;

    @Test
    public void testSimpleJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final Job simpleJob = (Job) getApplicationContext().getBean("simpleJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        JobParameters param = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        jobLauncher.run(simpleJob, param);
        stopWatch.stop();
        LOGGER.info(String.format("execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));
    }

    @Test
    public void testSimplePartition()throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        JobParameters param = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        jobLauncher.run(simplePartition, param);
        stopWatch.stop();
        LOGGER.info(String.format("execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));
    }


}
