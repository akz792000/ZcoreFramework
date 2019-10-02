package org.zcoreframework.batch.test.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.processor.AbstractItemProcessor;
import org.zcoreframework.batch.test.entity.StudentEntity;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class NormalLogErrorProcessor extends AbstractItemProcessor<StudentEntity, StudentEntity> {

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    protected StudentEntity doProcess(StudentEntity item) throws Exception {
        throw new Exception();
    }

    @Override
    protected void logError(StudentEntity item, Throwable e) throws Exception {
        item.setId(4L);
        item.setName("Processor.logError()");
        item.setFamily("Is Committed");
        item.setAge(32);
        repository.persist(item);
    }
}
