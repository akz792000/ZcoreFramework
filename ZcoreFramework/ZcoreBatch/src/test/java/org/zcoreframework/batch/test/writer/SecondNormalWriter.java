package org.zcoreframework.batch.test.writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class SecondNormalWriter extends AbstractItemWriter<StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(SecondNormalWriter.class);

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    protected void doWrite(List<? extends StudentEntity> items) throws Exception {
        LOGGER.info("NormalWriter.doWrite() is called.");
        items.forEach(item -> {
            item.setName(getStepExecution().getStepName());
            item.setFamily("Is Wrote");
            repository.merge(item);
        });
    }

    @Override
    protected void logError(List<? extends StudentEntity> items, Throwable e) throws Exception {
        LOGGER.error("Expected exception and change id of student object.");
        items.forEach(item -> {
            item.setId(null);
            item.setFamily("Failed");
            repository.merge(item);
        });
    }
}
