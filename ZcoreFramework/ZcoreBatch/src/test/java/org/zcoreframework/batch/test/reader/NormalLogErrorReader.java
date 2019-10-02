package org.zcoreframework.batch.test.reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.reader.DefaultItemReader;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.List;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class NormalLogErrorReader extends DefaultItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(NormalLogErrorReader.class);

    @RepositoryInstance
    private DefaultRepository repository;

    public NormalLogErrorReader() {
        super(null);
    }

    @Override
    protected List<StudentEntity> doRead(Integer loadLimit) throws Exception {
        throw new Exception();
    }

    @Override
    protected void logError(Integer readInterval, Throwable e) throws Exception {
        repository.persist(new StudentEntity(3L, "Reader.logError()", "Is Committed", 32));
    }
}
