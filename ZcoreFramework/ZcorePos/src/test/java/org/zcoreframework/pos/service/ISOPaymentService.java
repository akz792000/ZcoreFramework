package org.zcoreframework.pos.service;

import org.zcoreframework.base.annotation.Business;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.pos.annotation.ISOMethod;
import org.zcoreframework.pos.annotation.ISOService;
import org.zcoreframework.pos.data.ISOMessage;
import org.zcoreframework.pos.domain.ISOTestEntity;
import org.zcoreframework.pos.exception.PosStandardException;

/**
 *
 */
@ISOService(mti = "1200")
@Business("PS_ISOPaymentService")
public class ISOPaymentService implements InitializeAware {

    @RepositoryInstance
    private DefaultRepository repository;

    @ISOMethod(prCodes = {"500000"})
    public ISOMessage invoke(ISOMessage message) {

        return message.succeed();
    }

    @ISOMethod(prCodes = {"100001"})
    public ISOMessage invokePersist(ISOMessage message) {
        ISOTestEntity entity = new ISOTestEntity();

        entity.setCode("10001");

        repository.persist(entity);

        return message.succeed();
    }

    @ISOMethod(prCodes = {"100002"})
    public ISOMessage invokeFault(ISOMessage message) {
        ISOTestEntity entity = new ISOTestEntity();

        entity.setCode("10001");

        repository.persist(entity);
        throw new RuntimeException("always error");
    }

    @ISOMethod(prCodes = {"100003"})
    public ISOMessage invokeFallback(ISOMessage message) {
        ISOTestEntity entity = new ISOTestEntity();

        entity.setCode("10001");

        repository.persist(entity);
        throw new PosStandardException("101");
    }

}
