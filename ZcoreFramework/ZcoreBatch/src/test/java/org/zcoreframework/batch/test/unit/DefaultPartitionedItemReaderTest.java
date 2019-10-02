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
public class DefaultPartitionedItemReaderTest implements InitializeAware {

    private final Logger LOGGER = Logger.getLogger(DefaultPartitionedItemReaderTest.class);

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
    public void normalDefaultPartitionedItemReaderJobTest() throws Exception {
        final Job normalDefaultPartitionedItemReaderJob = (Job) getApplicationContext().getBean("normalDefaultPartitionedItemReaderJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(normalDefaultPartitionedItemReaderJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertEquals(20, studentList.size());
        studentList.forEach(student -> assertEquals("Is Wrote", student.getFamily()));
    }

    @Test
    public void chunkOrientedDefaultPartitionedItemReaderJobTest() throws Exception {
        final Job chunkOrientedDefaultPartitionedItemReaderJob = (Job) getApplicationContext().getBean("chunkOrientedDefaultPartitionedItemReaderJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(chunkOrientedDefaultPartitionedItemReaderJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertEquals(20, studentList.size());
        studentList.forEach(student -> assertEquals("Is Wrote", student.getFamily()));
    }

    @Test
    public void unsortedReadForDefaultPartitionedItemReaderJobTest() throws Exception {
        final Job unsortedReadForDefaultPartitionedItemReaderJob = (Job) getApplicationContext().getBean("unsortedReadForDefaultPartitionedItemReaderJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(unsortedReadForDefaultPartitionedItemReaderJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertEquals(20, studentList.size());
        studentList.forEach(student -> assertEquals("Is Wrote", student.getFamily()));
    }

    @Test
    public void nullReturnDefaultPartitionedItemReaderJobTest() throws Exception {
        final Job nullReturnDefaultPartitionedItemReaderJob = (Job) getApplicationContext().getBean("nullReturnDefaultPartitionedItemReaderJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(nullReturnDefaultPartitionedItemReaderJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        assertNull(testUtils.loadAllStudents());
    }

    @Test
    public void normalLogErrorDefaultPartitionedItemReaderJobTest() throws Exception {
        final Job normalLogErrorDefaultPartitionedItemReaderJob = (Job) getApplicationContext().getBean("normalLogErrorDefaultPartitionedItemReaderJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(normalLogErrorDefaultPartitionedItemReaderJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertEquals(20, studentList.size());
        studentList.forEach(student -> assertEquals("DefaultPartitionedItemReader.logError()", student.getName()));
        studentList.forEach(student -> assertEquals("Is Committed", student.getFamily()));
    }

    @Test
    public void chunkOrientedLogErrorDefaultPartitionedItemReaderJobTest() throws Exception {
        final Job chunkOrientedLogErrorDefaultPartitionedItemReaderJob = (Job) getApplicationContext().getBean("chunkOrientedLogErrorDefaultPartitionedItemReaderJob");
        final JobParameters jobParameters = new JobParametersBuilder().addDate("today", new Date()).toJobParameters();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final JobExecution jobExecution = jobLauncher.run(chunkOrientedLogErrorDefaultPartitionedItemReaderJob, jobParameters);
        stopWatch.stop();
        LOGGER.info(String.format("Execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

        assertEquals(COMPLETED, jobExecution.getStatus());

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertEquals(10, studentList.size());
        studentList.forEach(student -> assertEquals("DefaultPartitionedItemReader.logError()", student.getName()));
        studentList.forEach(student -> assertEquals("Is Committed", student.getFamily()));
    }
}
