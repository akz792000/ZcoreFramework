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
public class ChunkOrientedDefaultPartitionedItemReader extends DefaultPartitionedItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(ChunkOrientedDefaultPartitionedItemReader.class);

    @RepositoryInstance
    private DefaultRepository repository;

    private AtomicBoolean readFlag = new AtomicBoolean(false);

    public ChunkOrientedDefaultPartitionedItemReader() {
        super(2);
    }

    @Override
    protected Long getValue(StudentEntity item) {
        return item.getId();
    }

    @Override
    protected List<StudentEntity> sortedRead(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit) throws Exception {
        LOGGER.info("ChunkOrientedDefaultPartitionedItemReader.sortedRead() is called.");
        final List<StudentEntity> studentList = new ArrayList<>();
        for (int count = 0; (inclusiveStartValue <= inclusiveEndValue) && (count < loadLimit); inclusiveStartValue++, count++) {
            studentList.add(new StudentEntity(inclusiveStartValue, inclusiveStartValue.toString(), "Is Read", 32));
        }
        return studentList;
    }

    @Override
    protected void logError(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit, Throwable e) throws Exception {
        LOGGER.error(String.format("Unexpected method call of ChunkOrientedDefaultPartitionedItemReader.logError()! %d, %d,%d", inclusiveStartValue, inclusiveEndValue, loadLimit));
        repository.persist(new StudentEntity(3L, "Reader", "Failed", 1));
    }
}
