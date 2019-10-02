package org.zcoreframework.batch.test.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class NormalProcessor extends AbstractItemProcessor<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(NormalProcessor.class);

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    protected StudentEntity doProcess(StudentEntity item) throws Exception {
        LOGGER.info("NormalProcessor.doProcess() is called.");
        item.setFamily("Is Processed");
        return item;
    }

    @Override
    protected void logError(StudentEntity item, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of NormalProcessor.logError()!");
        repository.persist(new StudentEntity(33333333L, "Processor", "Failed", 1));
    }
}
