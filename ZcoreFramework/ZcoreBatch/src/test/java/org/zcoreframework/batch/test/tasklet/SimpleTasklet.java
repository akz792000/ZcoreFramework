package org.zcoreframework.batch.test.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.batch.tasklet.AbstractTasklet;

/**
 * Created by z.azadi on 11/6/2016.
 */
@Scope("step")
@Component
public class SimpleTasklet extends AbstractTasklet {
    @Override
    public void doExecute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("test ffff ");
        throw new Exception("test exception in Tasklet");
    }
}
