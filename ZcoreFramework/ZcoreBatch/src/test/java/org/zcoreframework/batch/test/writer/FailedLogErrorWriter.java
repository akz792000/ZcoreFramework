package org.zcoreframework.batch.test.writer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.test.entity.StudentEntity;
import org.zcoreframework.batch.writer.AbstractItemWriter;

import java.util.List;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class FailedLogErrorWriter extends AbstractItemWriter<StudentEntity> {

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    protected void doWrite(List<? extends StudentEntity> items) throws Exception {
        for (StudentEntity item : items) {
            item.setFamily("Should Rollback");
            repository.persist(item);
        }
        throw new Exception();
    }

    @Override
    protected void logError(List<? extends StudentEntity> items, Throwable e) throws Exception {
        for (StudentEntity item : items) {
            item.setName("Writer.logError()");
            repository.persist(item);
        }
        throw new RuntimeException();
    }
}
