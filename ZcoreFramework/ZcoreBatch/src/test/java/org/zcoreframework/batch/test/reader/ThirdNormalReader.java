package org.zcoreframework.batch.test.reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.reader.DefaultItemReader;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class ThirdNormalReader extends DefaultItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(ThirdNormalReader.class);

    @RepositoryInstance
    private DefaultRepository repository;

    private AtomicBoolean readFlag = new AtomicBoolean(false);

    public ThirdNormalReader() {
        super(null);
    }

    @Override
    protected List<StudentEntity> doRead(Integer loadLimit) throws Exception {
        if (!readFlag.getAndSet(true)) {
            LOGGER.info("ThirdNormalReader.sortedRead() is called for the first time.");
            final List<StudentEntity> items = new ArrayList<>();
            items.add(new StudentEntity(1L, "Thread", "Is Read", 32));
            items.add(new StudentEntity(2L, "Thread", "Is Read", 32));
            return items;
        }
        LOGGER.info("ThirdNormalReader.sortedRead() is called for the second time.");
        return null;
    }

    @Override
    protected void logError(Integer readInterval, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of ThirdNormalReader.logError()!");
        repository.persist(new StudentEntity(3L, "Reader", "Failed", 1));
    }
}
