package org.zcoreframework.batch.test.reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.reader.DefaultItemReader;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class TransactionalReader extends DefaultItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(TransactionalReader.class);

    private AtomicBoolean readFlag = new AtomicBoolean(false);

    @RepositoryInstance
    private DefaultRepository repository;

    public TransactionalReader() {
        super(null);
    }

    @Override
    @SuppressWarnings("Duplicates")
    protected List<StudentEntity> doRead(Integer loadLimit) throws Exception {
        repository.persist(new StudentEntity(1L, "Reader", "Should Rollback", 10));
        return null;
    }

    @Override
    protected void logError(Integer readInterval, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of TransactionalReader.logError()!");
        repository.persist(new StudentEntity(3L, "Reader", "Failed", 1));
    }
}
