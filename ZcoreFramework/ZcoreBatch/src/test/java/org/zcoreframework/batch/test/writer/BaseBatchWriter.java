package org.zcoreframework.batch.test.writer;

import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.test.domain.TestBatchEntity;
import org.zcoreframework.batch.writer.AbstractItemWriter;

import java.util.List;

/**
 * Created by z.azadi on 5/1/2016.
 */
public class BaseBatchWriter extends AbstractItemWriter<TestBatchEntity> {
    @RepositoryInstance
    private DefaultRepository defaultRepository;

    @Override
    public void doWrite(List<? extends TestBatchEntity> items) {
        for (TestBatchEntity testBatchEntity : items) {
            defaultRepository.persist(testBatchEntity);
        }
    }
}
