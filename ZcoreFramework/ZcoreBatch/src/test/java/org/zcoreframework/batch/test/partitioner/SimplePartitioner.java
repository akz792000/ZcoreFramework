package org.zcoreframework.batch.test.partitioner;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;
import org.zcoreframework.batch.partitioner.AbstractPartitioner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pooya
 *         date : 5/9/16
 */
@Component
public class SimplePartitioner extends AbstractPartitioner {

    @Override
    public Map<String, ExecutionContext> doPartition(int gridSize) throws Exception {
        Map<String, ExecutionContext> result = new HashMap<>();

        for (int i = 0; i < gridSize; i++) {
            ExecutionContext value = new ExecutionContext();
            int offset = 0;
            int step = 100 / gridSize;
            value.putInt("from", offset + i * step);
            value.putInt("to", offset + (i + 1) * step);
            result.put("partition" + i, value);
        }

        return result;
    }
}
