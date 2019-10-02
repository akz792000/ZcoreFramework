package org.zcoreframework.batch.test.util;

import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.function.TransactionCallbackWithoutResult;
import org.zcoreframework.batch.test.entity.StudentEntity;

import java.util.Collections;
import java.util.List;

import static java.lang.String.valueOf;
import static java.util.Collections.EMPTY_MAP;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.zcoreframework.batch.util.TransactionUtils.executeTransactionally;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/27/17
 */
@Component
public class TestUtils implements InitializeAware {

    @RepositoryInstance
    private DefaultRepository repository;

    public void clearStudentEntity() throws Exception {
        executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> repository.executeUpdate("deleteAll", Collections.emptyMap()));
    }

    public void persistTemporalStudent() throws Exception {
        executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> repository.persist(new StudentEntity(1L, "Temporal", "Is Committed", 32)));
    }

    public void persistTwoTemporalStudents() throws Exception {
        executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> {
            repository.persist(new StudentEntity(1L, "Temporal", "Is Committed", 32));
            repository.persist(new StudentEntity(2L, "Temporal", "Is Committed", 32));
        });
    }

    public void persistTwentyTemporalStudents() throws Exception {
        executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> {
            for (Long index = 1L; index.compareTo(20L) <= 0; index++) {
                repository.persist(new StudentEntity(index, valueOf(index), "Is Temporal", 32));
            }
        });
    }

    public void persistEightHundredTemporalStudents() throws Exception {
        executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> {
            for (Long index = 1L; index.compareTo(800L) <= 0; index++) {
                repository.persist(new StudentEntity(index, valueOf(index), "Is Temporal", 32));
            }
        });
    }

    public List<StudentEntity> loadAllStudents() {
        return (List<StudentEntity>) repository.loadResultList("selectAll", EMPTY_MAP);
    }
}
