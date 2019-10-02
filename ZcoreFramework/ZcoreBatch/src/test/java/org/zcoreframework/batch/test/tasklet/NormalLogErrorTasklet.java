package org.zcoreframework.batch.test.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.tasklet.AbstractTasklet;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.List;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class NormalLogErrorTasklet extends AbstractTasklet {

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    protected void doExecute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        throw new Exception();
    }

    @Override
    protected void logError(StepContribution stepContribution, ChunkContext chunkContext, Throwable e) throws Exception {
        repository.persist(new StudentEntity(6L, "Tasklet.logError()", "Is Committed", 32));
    }
}
