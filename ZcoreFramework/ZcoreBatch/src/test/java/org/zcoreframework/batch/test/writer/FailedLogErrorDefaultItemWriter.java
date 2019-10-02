package org.zcoreframework.batch.test.writer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.test.entity.StudentEntity;
import org.zcoreframework.batch.writer.DefaultItemWriter;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class FailedLogErrorDefaultItemWriter extends DefaultItemWriter<StudentEntity> {

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    protected void doWrite(StudentEntity item) throws Exception {
        item.setFamily("Should Rollback");
        repository.persist(item);
    }

    @Override
    protected void logError(StudentEntity item, Throwable e) throws Exception {
        item.setName("Writer.logError()");
        repository.persist(item);
        throw new RuntimeException();
    }
}
