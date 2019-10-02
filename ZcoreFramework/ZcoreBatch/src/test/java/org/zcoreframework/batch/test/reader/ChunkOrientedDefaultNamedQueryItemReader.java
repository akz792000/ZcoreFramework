package org.zcoreframework.batch.test.reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.reader.DefaultNamedQueryItemReader;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class ChunkOrientedDefaultNamedQueryItemReader extends DefaultNamedQueryItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(ChunkOrientedDefaultNamedQueryItemReader.class);

    private Long exclusiveStartId = 0L;

    @RepositoryInstance
    private DefaultRepository repository;

    public ChunkOrientedDefaultNamedQueryItemReader() {
        super(2);
    }

    @Override
    protected String getQueryName() throws Exception {
        return "zcore.batch.selectGreaterThan";
    }

    @Override
    protected Map<String, Object> getQueryParameters(Integer loadLimit) throws Exception {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("f", exclusiveStartId);
        exclusiveStartId += loadLimit;
        return parameters;
    }

    @Override
    protected void logError(Integer readInterval, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of ChunkOrientedDefaultNamedQueryItemReader.logError()!");
        repository.persist(new StudentEntity(3L, "Reader", "Failed", 1));
    }
}
