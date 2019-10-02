package org.zcoreframework.batch.test.writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class NormalDefaultItemWriter extends DefaultItemWriter<StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(NormalDefaultItemWriter.class);

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    protected void doWrite(StudentEntity item) throws Exception {
        LOGGER.info("NormalDefaultItemWriter.doWrite() is called.");
        item.setName(getStepExecution().getStepName());
        item.setFamily("Is Wrote");
        repository.persist(item);
    }

    @Override
    protected void logError(StudentEntity item, Throwable e) throws Exception {
        LOGGER.error("Expected exception and change id of student object.");
        item.setId(null);
        item.setFamily("Failed");
        repository.persist(item);
    }
}
