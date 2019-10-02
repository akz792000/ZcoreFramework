package org.zcoreframework.batch.test.partitioner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class IncorrectMinMaxValuePartitioner extends DefaultPartitioner {

    private static final Log LOGGER = LogFactory.getLog(IncorrectMinMaxValuePartitioner.class);

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    protected AggregationInfo getAggregationInfo() throws Exception {
        LOGGER.info("NormalPartitioner.getAggregationInfo() is called.");
        return new AggregationInfo(10L, 9L, 7L);
    }

    @Override
    protected void logError(int gridSize, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of NormalPartitioner.logError()!");
        repository.persist(new StudentEntity(2L, "Partitioner", "Failed", 1));
    }
}
