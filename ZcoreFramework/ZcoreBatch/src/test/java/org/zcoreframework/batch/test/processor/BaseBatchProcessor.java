package org.zcoreframework.batch.test.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.exception.ClientException;
import org.zcoreframework.batch.exceptions.FilterException;
import org.zcoreframework.batch.processor.AbstractItemProcessor;
import org.zcoreframework.batch.test.domain.BaseBatchEntity;
import org.zcoreframework.batch.test.domain.TestBatchEntity;

/**
 * Created by z.azadi on 5/1/2016.
 */
@StepScope
@Component("s_baseBatchProcessor")
public class BaseBatchProcessor extends AbstractItemProcessor<BaseBatchEntity, TestBatchEntity>  {

    @RepositoryInstance
    private DefaultRepository defaultRepository;

    @Override
    public TestBatchEntity doProcess(BaseBatchEntity input) throws Exception {
        TestBatchEntity testBatchEntity = new TestBatchEntity();
        testBatchEntity.setContractId(input.getContractId());
        testBatchEntity.setSTATUS(input.getSTATUS());
        testBatchEntity.setSample(input.getSample() + "::Active ::::::");
       if(input.getOrgId()==1L){
           ClientException.deliver("ssss");
        }
        if (input.getOrgId() == null) {
            throw new IllegalArgumentException();
        }
         if(input.getOrgId().equals(111L)){
            throw  new FilterException();
        }

        testBatchEntity.setOrgId(input.getOrgId());
        return testBatchEntity;
    }
}
