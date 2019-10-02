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
public class UnsortedReadForDefaultPartitionedItemReader extends DefaultPartitionedItemReader<StudentEntity, StudentEntity> {

    private static final Log LOGGER = LogFactory.getLog(UnsortedReadForDefaultPartitionedItemReader.class);

    @RepositoryInstance
    private DefaultRepository repository;

    public UnsortedReadForDefaultPartitionedItemReader() {
        super(2);
    }

    @Override
    protected Long getValue(StudentEntity item) {
        return item.getId();
    }

    @Override
    protected List<StudentEntity> sortedRead(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit) throws Exception {
        LOGGER.info("UnsortedReadForDefaultPartitionedItemReader.sortedRead() is called.");
        final List<StudentEntity> studentList = new ArrayList<>();
        for (int count = 0; (inclusiveStartValue <= inclusiveEndValue) && (count < loadLimit); inclusiveStartValue++, count++) {
            studentList.add(new StudentEntity(inclusiveStartValue, inclusiveStartValue.toString(), "Is Read", 32));
        }
        studentList.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
        return studentList;
    }

    @Override
    protected void logError(Long inclusiveStartValue, Long inclusiveEndValue, Integer loadLimit, Throwable e) throws Exception {
        LOGGER.error("Unexpected method call of UnsortedReadForDefaultPartitionedItemReader.logError()!");
        repository.persist(new StudentEntity(3L, "Reader", "Failed", 1));
    }
}
