package org.zcoreframework.batch.test.unit;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.batch.test.entity.StudentEntity;
import org.zcoreframework.batch.test.util.TestUtils;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.batch.core.BatchStatus.COMPLETED;
import static org.springframework.batch.core.BatchStatus.FAILED;
import static org.zcoreframework.base.util.ApplicationContextUtils.getApplicationContext;


/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/testContext.xml"})
@SuppressWarnings({"Duplicates", "SpringJavaAutowiringInspection"})
public class DefaultItemWriterTest implements InitializeAware {

    private final Logger LOGGER = Logger.getLogger(DefaultItemWriterTest.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        testUtils.clearStudentEntity();
        LOGGER.info("All students are deleted.");
    }

    @After
    public void tearDown() throws Exception {
        LOGGER.info("All test is ran.");
    }

    @Ignore("DefaultItemWriter is written based on Nested Transaction, and Nested Transaction is not supported in the framework")
    @Test
    public void normalDefaultItemWriterJobTest() throws Exception {
        final Job normalDefaultItemWriterJob = (Job) getApplicationContext().getBean("normalDefaultItemWriterJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(normalDefaultItemWriterJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(2, studentList.size());
        studentList.forEach(student -> assertEquals("Is Wrote", student.getFamily()));
    }

    @Ignore("DefaultItemWriter is written based on Nested Transaction, and Nested Transaction is not supported in the framework")
    @Test
    public void normalLogErrorDefaultItemWriterJobTest() throws Exception {
        final Job normalLogErrorDefaultItemWriterJob = (Job) getApplicationContext().getBean("normalLogErrorDefaultItemWriterJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(normalLogErrorDefaultItemWriterJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(2, studentList.size());
        int isWroteCount = 0;
        int isRollbackCount = 0;
        for (StudentEntity student : studentList) {
            if (student.getFamily().equals("Is Wrote")) isWroteCount++;
            if (student.getFamily().equals("Is Rollback")) isRollbackCount++;
        }
        assertEquals(1, isWroteCount);
        assertEquals(1, isRollbackCount);
    }

    @Ignore("DefaultItemWriter is written based on Nested Transaction, and Nested Transaction is not supported in the framework")
    @Test
    public void failedLogErrorDefaultItemWriterJobTest() throws Exception {
        final Job failedLogErrorDefaultItemWriterJob = (Job) getApplicationContext().getBean("failedLogErrorDefaultItemWriterJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(failedLogErrorDefaultItemWriterJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(FAILED, jobExecution.getStatus());

        assertNull(testUtils.loadAllStudents());
    }
}
