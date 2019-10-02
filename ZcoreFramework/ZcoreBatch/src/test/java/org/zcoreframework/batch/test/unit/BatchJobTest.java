package org.zcoreframework.batch.test.unit;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
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
import static org.zcoreframework.base.util.ApplicationContextUtils.getApplicationContext;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/testContext.xml"})
@SuppressWarnings({"Duplicates", "SpringJavaAutowiringInspection"})
public class BatchJobTest implements InitializeAware {

    private final Logger LOGGER = Logger.getLogger(BatchJobTest.class);

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

    @Test
    public void normalJobTest() throws Exception {
        final Job normalJob = (Job) getApplicationContext().getBean("normalJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(normalJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        int commitCount = 0, failedCount = 0;
        for (StudentEntity student : testUtils.loadAllStudents()) {
            if (student.getFamily().equals("Is Wrote")) {
                commitCount++;
            } else {
                final String name = student.getName();
                assertNotEquals(name, "Partitioner");
                assertNotEquals(name, "Reader");
                assertNotEquals(name, "Processor");
                failedCount++;
            }
        }
        assertEquals(1, commitCount);
        assertEquals(4, failedCount);
    }

    @Test
    public void highConcurrentJobTest() throws Exception {
        testUtils.persistEightHundredTemporalStudents();

        final Job highConcurrentJob = (Job) getApplicationContext().getBean("highConcurrentJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(highConcurrentJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(800, studentList.size());
        studentList.forEach(student -> assertEquals("Is Wrote", student.getFamily()));
    }
}
