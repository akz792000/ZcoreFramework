package org.zcoreframework.batch.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.test.entity.StudentEntity;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author pooya
 *         date : 4/19/16
 */

@ContextConfiguration(locations = "classpath:/META-INF/h2TextContext.xml")
@TransactionConfiguration(defaultRollback = false, transactionManager = "org.zcoreframework.base.transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBatchOperation implements InitializeAware {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @RepositoryInstance
    private DefaultRepository repository;

    @Autowired
    private JobLauncher jobLauncher;


    @Resource(name = "simpleJob")
    private Job simpleJob;

    @Resource(name = "simplePartition")
    private Job simplePartitionedJob;

    @Transactional
    @Test
    public void deleteAll() {
        repository.executeUpdate("deleteAll", Collections.emptyMap());
    }

    @Transactional
    @Test
    public void persistAll() {
        for (int i = 0; i < 100; i++) {
            StudentEntity entity = new StudentEntity();
            entity.setAge(i);
            entity.setName("Name" + i);
            entity.setFamily("Family" + i);
            repository.persist(entity);
        }
    }

    @Test
    @Transactional
    public void retrieveAll() {
        List<?> selectAll = repository.loadResultList("selectAll", Collections.EMPTY_MAP);
        System.out.println(selectAll);
    }

    @Test
    public void executeTestSomething() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        jobLauncher.run(simplePartitionedJob, new JobParameters());
        stopWatch.stop();
        LOGGER.info(String.format("execute takes %d ms!", stopWatch.getLastTaskTimeMillis()));

    }

    @Test
    public void testScheduler() {
        while (true) {

        }
    }
}
