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
public class NormalDefaultPartitionedItemReader extends DefaultPartitionedItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(NormalDefaultPartitionedItemReader.class);

    @RepositoryInstance
    private DefaultRepository repository;

    private AtomicBoolean readFlag = new AtomicBoolean(false);

    public NormalDefaultPartitionedItemReader() {
        super(null);
    }

    @Override
    protected Long getValue(StudentEntity item) {
        return item.getId();
    }

    @Override
    protected List<StudentEntity> sortedRead(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit) throws Exception {
        if (!readFlag.getAndSet(true)) {
            LOGGER.info("NormalDefaultPartitionedItemReader.sortedRead() is called for the first time.");
            final List<StudentEntity> studentList = new ArrayList<>();
            for (; inclusiveStartValue <= inclusiveEndValue; inclusiveStartValue++) {
                studentList.add(new StudentEntity(inclusiveStartValue, inclusiveStartValue.toString(), "Is Read", 32));
            }
            return studentList;
        }
        LOGGER.info("NormalDefaultPartitionedItemReader.sortedRead() is called for the second time.");
        return null;
    }

    @Override
    protected void logError(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of NormalDefaultPartitionedItemReader.logError()!");
        repository.persist(new StudentEntity(3L, "Reader", "Failed", 1));
    }
}
