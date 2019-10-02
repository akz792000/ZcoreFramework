package org.zcoreframework.batch.test.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.processor.AbstractItemProcessor;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class TransactionalProcessor extends AbstractItemProcessor<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(TransactionalProcessor.class);

    private AtomicBoolean readFlag = new AtomicBoolean(false);

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    @SuppressWarnings("Duplicates")
    protected StudentEntity doProcess(StudentEntity item) throws Exception {
        item.setName("Processor");
        item.setFamily("Should Rollback");
        repository.persist(item);
        return null;
    }

    @Override
    protected void logError(StudentEntity item, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of TransactionalProcessor.logError()!");
        repository.persist(new StudentEntity(4L, "Processor", "Failed", 1));
    }
}
