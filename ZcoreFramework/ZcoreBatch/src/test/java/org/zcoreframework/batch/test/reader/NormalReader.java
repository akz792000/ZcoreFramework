package org.zcoreframework.batch.test.reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.batch.reader.DefaultPartitionedItemReader;
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
public class NormalReader extends DefaultPartitionedItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(NormalReader.class);

    @RepositoryInstance
    private DefaultRepository repository;

    private AtomicBoolean readFlag = new AtomicBoolean(false);

    public NormalReader() {
        super(null);
    }

    @Override
    protected Long getValue(StudentEntity item) {
        LOGGER.info("NormalReader.getValue() is called.");
        return item.getId();
    }

    @Override
    protected List<StudentEntity> sortedRead(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit) throws Exception {
        if (!readFlag.getAndSet(true)) {
            LOGGER.info("NormalReader.sortedRead() is called for the first time.");
            final List<StudentEntity> items = new ArrayList<>();
            items.add(new StudentEntity(1L, "Thread", "Is Read", 32));
            return items;
        }
        LOGGER.info("NormalReader.sortedRead() is called for the second time.");
        return null;
    }

    @Override
    protected void logError(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of NormalReader.logError()!");
        repository.persist(new StudentEntity(3L, "Reader", "Failed", 1));
    }
}
