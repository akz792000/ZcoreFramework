package org.zcoreframework.batch.test.partitioner;

import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.model.AggregationInfo;
import org.zcoreframework.batch.partitioner.DefaultPartitioner;
import org.zcoreframework.batch.test.entity.StudentEntity;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Component
public class FailedLogErrorPartitioner extends DefaultPartitioner {

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    protected AggregationInfo getAggregationInfo() throws Exception {
        repository.persist(new StudentEntity(1L, "Partitioner", "Should Rollback", 10));
        throw new Exception();
    }

    @Override
    protected void logError(int gridSize, Throwable e) throws Exception {
        repository.persist(new StudentEntity(2L, "Partitioner.logError()", "Should Rollback", 10));
        throw new RuntimeException();
    }
}
