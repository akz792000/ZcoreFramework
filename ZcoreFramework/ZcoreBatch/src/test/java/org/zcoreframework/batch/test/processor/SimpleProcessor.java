package org.zcoreframework.batch.test.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.batch.exceptions.FilterException;
import org.zcoreframework.batch.processor.AbstractItemProcessor;
import org.zcoreframework.batch.test.entity.StudentEntity;

/**
 * @author pooya
 *         date : 5/9/16
 */
@Scope("step")
@Component
public class SimpleProcessor extends AbstractItemProcessor<StudentEntity, StudentEntity> {

    @Override
    public StudentEntity doProcess(StudentEntity item) throws Exception {
        item.setName(item.getName() + "Processed");
        if(item.getId().equals(1L))
            throw  new FilterException();
        return item;
    }
}
