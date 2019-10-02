package org.zcoreframework.batch.test.reader;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.reader.AbstractQueryNameItemReader;
import org.zcoreframework.batch.test.domain.BaseBatchEntity;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by z.azadi on 5/1/2016.
 */
@StepScope
@Component("s_batchBaseReader")
public class BatchBaseReader extends AbstractQueryNameItemReader<BaseBatchEntity> {

    @RepositoryInstance
    private DefaultRepository defaultRepository;
    @Override
    public Map<String, Object> getParameters(StepExecution stepExecution) {
        Map<String, Object> map = new HashMap<>();
        super.setQueryName("BaseBatchEntity.loadActive1");
        return map;

    }

    @Override
    public Map<String, AbstractMap.SimpleImmutableEntry<Object, Object>> getParametersTemporalType(StepExecution stepExecution) {
        return null;
    }

    public boolean validation(){
        /*TestBatchEntity testBatchEntity = new TestBatchEntity();
        testBatchEntity.setContractId(222L);
        testBatchEntity.setSTATUS(333L);
        testBatchEntity.setSample( "::Reader ::::::");
      *//* if(input.getOrgId()==1L){
           ClientException.deliver("ssss");
        }*//*
       *//* if (input.getOrgId() == null) {
            throw new IllegalArgumentException();
        }*//*
        *//*  if(input.getOrgId().equals(111L)){
            throw  new FilterException();
        }*//*

        testBatchEntity.setOrgId(444l);
        defaultRepository.persist(testBatchEntity);*/

        return true;
    }






}
