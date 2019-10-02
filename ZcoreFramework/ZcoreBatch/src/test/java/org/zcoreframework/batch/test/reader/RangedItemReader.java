package org.zcoreframework.batch.test.reader;

import org.springframework.batch.core.StepExecution;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.batch.reader.AbstractQueryNameItemReader;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pooya
 *         date : 5/9/16
 */

@Scope(value="step")
@Component
public class RangedItemReader extends
        AbstractQueryNameItemReader<StudentEntity> {

    @Override
    public Map<String, Object> getParameters(StepExecution stepExecution) {
        setQueryName("selectByRange");
        Map<String, Object> map = new HashMap<>();
        int from = stepExecution.getExecutionContext().getInt("from");
        int to = stepExecution.getExecutionContext().getInt("to");
        map.put("f", from);
        map.put("t", to);
        return map;
    }

    @Override
    public Map<String, AbstractMap.SimpleImmutableEntry<Object, Object>> getParametersTemporalType(StepExecution stepExecution) {
        return null;
    }


}
