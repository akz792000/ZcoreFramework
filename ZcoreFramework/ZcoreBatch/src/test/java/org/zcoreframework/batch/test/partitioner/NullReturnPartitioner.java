package org.zcoreframework.batch.test.partitioner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.zcoreframework.batch.model.AggregationInfo;
import org.zcoreframework.batch.partitioner.DefaultPartitioner;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Component
public class NullReturnPartitioner extends DefaultPartitioner {

    private static final Log LOGGER = LogFactory.getLog(NullReturnPartitioner.class);

    @Override
    protected AggregationInfo getAggregationInfo() throws Exception {
        return null;
    }

    @Override
    protected void logError(int gridSize, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call!");
    }
}
