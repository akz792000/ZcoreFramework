package org.zcoreframework.batch.test.reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.reader.DefaultPartitionedNamedQueryItemReader;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class ChunkOrientedDefaultPartitionedNamedQueryItemReader extends DefaultPartitionedNamedQueryItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(ChunkOrientedDefaultPartitionedNamedQueryItemReader.class);

    @RepositoryInstance
    private DefaultRepository repository;

    public ChunkOrientedDefaultPartitionedNamedQueryItemReader() {
        super(2);
    }

    @Override
    protected String getSortedQueryName() throws Exception {
        return "zcore.batch.selectByRangeInclusive";
    }

    @Override
    protected Map<String, Object> getQueryParameters(Long inclusiveStartValue, Long inclusiveEndValue) throws Exception {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("f", inclusiveStartValue);
        parameters.put("t", inclusiveEndValue);

        return parameters;
    }

    @Override
    protected Long getValue(StudentEntity item) {
        return item.getId();
    }

    @Override
    protected void logError(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of ChunkOrientedDefaultPartitionedNamedQueryItemReader.logError()!");
        repository.persist(new StudentEntity(3L, "Reader", "Failed", 1));
    }
}
