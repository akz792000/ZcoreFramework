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

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@Scope("step")
@Component
public class ChunkOrientedLogErrorDefaultPartitionedItemReader extends DefaultPartitionedItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(ChunkOrientedLogErrorDefaultPartitionedItemReader.class);

    @RepositoryInstance
    private DefaultRepository repository;

    public ChunkOrientedLogErrorDefaultPartitionedItemReader() {
        super(2);
    }

    @Override
    protected Long getValue(StudentEntity item) {
        return item.getId();
    }

    @Override
    protected List<StudentEntity> sortedRead(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit) throws Exception {
        throw new Exception();
    }

    @Override
    protected void logError(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit, Throwable e) throws Exception {
        final List<StudentEntity> studentList = new ArrayList<>();
        for (int count = 0; (inclusiveStartValue <= inclusiveEndValue) && (count < loadLimit); inclusiveStartValue++, count++) {
            repository.persist(new StudentEntity(inclusiveStartValue, "DefaultPartitionedItemReader.logError()", "Is Committed", 32));
        }
    }
}
