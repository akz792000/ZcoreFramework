package org.zcoreframework.batch.test.writer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.test.entity.StudentEntity;
import org.zcoreframework.batch.writer.DefaultItemWriter;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class NormalLogErrorDefaultItemWriter extends DefaultItemWriter<StudentEntity> {

    @RepositoryInstance
    private DefaultRepository repository;

    private AtomicBoolean readFlag = new AtomicBoolean(false);

    @Override
    protected void doWrite(StudentEntity item) throws Exception {
        item.setName(getStepExecution().getStepName());
        item.setFamily("Is Wrote");
        repository.persist(item);
    }

    @Override
    protected void logError(StudentEntity item, Throwable e) throws Exception {
        repository.persist(new StudentEntity(2L, item.getName(), "Is Rollback", item.getAge()));
    }
}
