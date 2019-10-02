package org.zcoreframework.batch.partitioner;

import org.springframework.batch.item.ExecutionContext;
import org.zcoreframework.batch.model.AggregationInfo;
import org.zcoreframework.batch.model.Partition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;
import static java.lang.String.format;
import static java.util.Collections.emptyMap;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/22/17
 */

public abstract class DefaultPartitioner extends AbstractPartitioner {

    public static final String PARTITION_MODEL = "PartitionModel";

    @Override
    protected final Map<String, ExecutionContext> doPartition(int gridSize) throws Exception {
        final AggregationInfo aggregationInfo = getAggregationInfo();
        if ((aggregationInfo == null) || (aggregationInfo.getTotalCount() == null)) return emptyMap();

        final int totalCount = aggregationInfo.getTotalCount().intValue();
        if ((totalCount <= 0) || (gridSize <= 0)) return emptyMap();

        final List<Partition> partitionList = createPartitions(aggregationInfo, min(gridSize, totalCount));
        if (partitionList.isEmpty()) return emptyMap();

        final Map<String, ExecutionContext> partitionMap = new HashMap<>(partitionList.size());
        partitionList.forEach(partition -> {
            partitionMap.put(format("Thread_%d", partition.getPartitionNumber()), createThreadExecutionContext(partition));
        });
        return partitionMap;
    }

    protected abstract AggregationInfo getAggregationInfo() throws Exception;

    private List<Partition> createPartitions(AggregationInfo aggregationInfo, int gridSize) {
        final List<Partition> partitionList = new ArrayList<>();

        Long endValue;
        Long startValue = aggregationInfo.getMinValue();
        final Long maxValue = aggregationInfo.getMaxValue();
        final Long chunkSize = (maxValue - startValue) / gridSize;

        for (int partitionNumber = 0; (startValue.compareTo(maxValue) <= 0); partitionNumber++) {
            endValue = startValue + chunkSize;
            partitionList.add(new Partition(startValue, endValue, partitionNumber));
            startValue = endValue + 1;
        }

        return partitionList;
    }

    private ExecutionContext createThreadExecutionContext(Partition partition) {
        final ExecutionContext threadContext = new ExecutionContext();
        threadContext.put(PARTITION_MODEL, partition);
        addToThreadExecutionContext(threadContext, partition);
        return threadContext;
    }

    /**
     * If you want to add any object to thread context, you can override this method.
     */
    protected void addToThreadExecutionContext(ExecutionContext threadContext, Partition partition) {
        // There is not any default implementation.
    }
}
