package org.zcoreframework.batch.test.writer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.test.entity.StudentEntity;
import org.zcoreframework.batch.writer.AbstractItemWriter;

import java.util.List;

/**
 * @author pooya
 *         date : 5/9/16
 */
@Scope("step")
@Component
public class SimpleWriter extends AbstractItemWriter<StudentEntity> {

    @RepositoryInstance
    private DefaultRepository repository;

    @Override
    public void doWrite(List<? extends StudentEntity> items) {
        System.out.println("In Writer " + items.size());
        items.forEach(repository::merge);
    }
}