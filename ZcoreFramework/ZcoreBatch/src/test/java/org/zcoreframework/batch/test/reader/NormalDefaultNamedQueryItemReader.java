package org.zcoreframework.batch.test.reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.reader.DefaultNamedQueryItemReader;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.Map;

import static java.util.Collections.emptyMap;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class NormalDefaultNamedQueryItemReader extends DefaultNamedQueryItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(NormalDefaultNamedQueryItemReader.class);

    @RepositoryInstance
    private DefaultRepository repository;

    public NormalDefaultNamedQueryItemReader() {
        super(null);
    }

    @Override
    protected String getQueryName() throws Exception {
        return "selectAll";
    }

    @Override
    protected Map<String, Object> getQueryParameters(Integer loadLimit) throws Exception {
        return emptyMap();
    }

    @Override
    protected void logError(Integer readInterval, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of NormalDefaultNamedQueryItemReader.logError()!");
        repository.persist(new StudentEntity(3L, "Reader", "Failed", 1));
    }
}
