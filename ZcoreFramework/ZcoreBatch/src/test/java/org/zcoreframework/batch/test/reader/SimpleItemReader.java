package org.zcoreframework.batch.test.reader;

import org.springframework.batch.core.StepExecution;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.batch.reader.AbstractQueryNameItemReader;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;

/**
 * @author pooya
 *         date : 5/9/16
 */
@Scope("step")
@Component
public class SimpleItemReader extends AbstractQueryNameItemReader<StudentEntity> {
    protected SimpleItemReader() {
        super("selectAll", Collections.emptyMap());
    }

    @Override
    public Map<String, Object> getParameters(StepExecution stepExecution) {
        return null;
    }

    @Override
    public Map<String, AbstractMap.SimpleImmutableEntry<Object, Object>> getParametersTemporalType(StepExecution stepExecution) {
        return null;
    }


}
