package org.zcoreframework.batch.test.unit;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.exception.TransactionException;
import org.zcoreframework.base.function.TransactionCallbackWithoutResult;
import org.zcoreframework.batch.util.TransactionUtils;
import org.zcoreframework.batch.test.entity.StudentEntity;
import org.zcoreframework.batch.test.util.TestUtils;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.transaction.annotation.Propagation.*;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/testContext.xml"})
@SuppressWarnings({"Duplicates", "SpringJavaAutowiringInspection"})
public class TransactionUtilsTest implements InitializeAware {

    private final Logger LOGGER = Logger.getLogger(TransactionUtilsTest.class);

    @RepositoryInstance
    private DefaultRepository repository;

    @Autowired
    private TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        testUtils.clearStudentEntity();
        LOGGER.info("All students are deleted.");
    }

    @After
    public void tearDown() throws Exception {
        LOGGER.info("All test is ran.");
    }

    @Test
    public void executeReadOnlyNormallyTest() throws Exception {
        TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) status -> {
            repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
        });
        assertNull(testUtils.loadAllStudents());
    }

    @Test
    public void executeTransactionallyNormallyTest() throws Exception {
        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) status -> {
            repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity student = studentList.get(0);
        assertEquals("Should", student.getName());
        assertEquals("Persist", student.getFamily());
    }

    @Test
    public void twoLevelInnerTransactionTest() throws Exception {
        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
            TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) innerStatus -> {
                repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
            });
            repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity student = studentList.get(0);
        assertEquals("Should", student.getName());
        assertEquals("Persist", student.getFamily());
    }

    @Test
    public void reverseTwoLevelInnerRequiredTransactionTest() throws Exception {
        TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) outerStatus -> {
            TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) innerStatus -> {
                repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
            });
            repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
        });

        assertNull(testUtils.loadAllStudents());
    }

    @Test
    public void reverseTwoLevelInnerRequiresNewTransactionTest() throws Exception {
        TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) outerStatus -> {
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) innerStatus -> {
                repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
            });
            repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity student = studentList.get(0);
        assertEquals("Should", student.getName());
        assertEquals("Persist", student.getFamily());
    }

    @Test
    public void threeLevelInnerRequiredTransactionTest() throws Exception {
        TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) status -> {
            TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
                TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) innerStatus -> {
                    repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
                });
                repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
            });
            repository.persist(new StudentEntity(11L, "Should", "Rollback", 10));
        });

        assertNull(testUtils.loadAllStudents());
    }

    @Test
    public void threeLevelInnerRequiresNewTransactionTest() throws Exception {
        TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) status -> {
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) outerStatus -> {
                TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) innerStatus -> {
                    repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
                });
                repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
            });
            repository.persist(new StudentEntity(11L, "Should", "Rollback", 10));
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity student = studentList.get(0);
        assertEquals("Should", student.getName());
        assertEquals("Persist", student.getFamily());
    }

    @Test
    public void reverseThreeLevelInnerRequiredTransactionTest() throws Exception {
        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) status -> {
            TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) outerStatus -> {
                TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) innerStatus -> {
                    repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
                });
                repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
            });
            repository.persist(new StudentEntity(2L, "Should", "Persist", 32));
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity student = studentList.get(0);
        assertEquals("Should", student.getName());
        assertEquals("Persist", student.getFamily());
    }

    @Test
    public void reverseThreeLevelInnerRequiresNewTransactionTest() throws Exception {
        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) status -> {
            TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) outerStatus -> {
                TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) innerStatus -> {
                    repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
                });
                repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
            });
            repository.persist(new StudentEntity(2L, "Should", "Persist", 32));
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(2, studentList.size());
        studentList.forEach(student -> {
            assertEquals("Should", student.getName());
            assertEquals("Persist", student.getFamily());
        });
    }

    @Test
    public void fourLevelInnerTransactionTest() throws Exception {
        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerOuterStatus -> {
            repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
            TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) outerStatus -> {
                repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
                TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) innerStatus -> {
                    repository.persist(new StudentEntity(2L, "Should", "Persist", 32));
                    TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) innerInnerStatus -> {
                        repository.persist(new StudentEntity(11L, "Should", "Rollback", 10));
                    });
                    repository.persist(new StudentEntity(3L, "Should", "Persist", 32));
                });
                repository.persist(new StudentEntity(12L, "Should", "Rollback", 10));
            });
            repository.persist(new StudentEntity(4L, "Should", "Persist", 32));
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(4, studentList.size());
        studentList.forEach(student -> {
            assertEquals("Should", student.getName());
            assertEquals("Persist", student.getFamily());
        });
    }

    @Test
    public void reverseFourLevelInnerTransactionTest() throws Exception {
        TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) outerOuterStatus -> {
            repository.persist(new StudentEntity(10L, "Should", "Rollback", 10));
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) outerStatus -> {
                repository.persist(new StudentEntity(1L, "Should", "Persist", 32));
                TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) innerStatus -> {
                    repository.persist(new StudentEntity(11L, "Should", "Rollback", 10));
                    TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) innerInnerStatus -> {
                        repository.persist(new StudentEntity(2L, "Should", "Persist", 32));
                    });
                    repository.persist(new StudentEntity(12L, "Should", "Rollback", 10));
                });
                repository.persist(new StudentEntity(3L, "Should", "Persist", 32));
            });
            repository.persist(new StudentEntity(14L, "Should", "Rollback", 10));
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(3, studentList.size());
        studentList.forEach(student -> {
            assertEquals("Should", student.getName());
            assertEquals("Persist", student.getFamily());
        });
    }

    @Test
    public void twoLevelOptimisticLockingWithOneMergeTest() throws Exception {
        testUtils.persistTemporalStudent();
        final StudentEntity student = testUtils.loadAllStudents().get(0);

        TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) outerStatus -> {
            student.setName("First Layer");
            student.setFamily("Should Overwritten");
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> {
                student.setName("Second Layer");
                student.setFamily("Should Commit");
                repository.merge(student);
            });
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity loadedStudent = studentList.get(0);
        assertEquals("Second Layer", loadedStudent.getName());
        assertEquals("Should Commit", loadedStudent.getFamily());
    }

    @Test(expected = JpaOptimisticLockingFailureException.class)
    public void twoLevelOptimisticLockingWithTwoMergeTest() throws Exception {
        testUtils.persistTemporalStudent();
        final StudentEntity student = testUtils.loadAllStudents().get(0);

        TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) outerStatus -> {
            student.setName("First Layer");
            student.setFamily("Should Rollback");
            repository.merge(student);
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> {
                student.setName("Second Layer");
                student.setFamily("Should Rollback");
                repository.merge(student);
            });
        });
    }

    @Test(expected = JpaOptimisticLockingFailureException.class)
    public void twoLevelOptimisticLockingByLoadingInAnotherTransactionTest() throws Exception {
        testUtils.persistTemporalStudent();

        TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) outerStatus -> {
            final StudentEntity student = testUtils.loadAllStudents().get(0);
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> {
                student.setName("Second Layer");
                student.setFamily("Should Rollback");
                repository.merge(student);
            });
        });
    }

    @Test
    public void twoLevelInnerMergeTransactionTest() throws Exception {
        testUtils.persistTemporalStudent();
        final StudentEntity student = testUtils.loadAllStudents().get(0);

        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
            student.setName("First Layer");
            student.setFamily("Should Overwrite");
            repository.merge(student);
            TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) status -> {
                student.setName("Second Layer");
                student.setFamily("Should Commit");
                repository.merge(student);
            });
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity loadedStudent = studentList.get(0);
        assertEquals("Second Layer", loadedStudent.getName());
        assertEquals("Should Commit", loadedStudent.getFamily());
    }

    @Test(expected = JpaOptimisticLockingFailureException.class)
    public void threeLevelOptimisticLockingTest() throws Exception {
        testUtils.persistTemporalStudent();
        final StudentEntity student = testUtils.loadAllStudents().get(0);

        TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) outerStatus -> {
            student.setName("First Layer");
            student.setFamily("Should Rollback");
            repository.merge(student);
            TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) status -> {
                student.setName("Second Layer");
                student.setFamily("Should Rollback");
                repository.merge(student);
                TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) innerStatus -> {
                    student.setName("Third Layer");
                    student.setFamily("Should Rollback");
                    repository.merge(student);
                });
            });
        });
    }

    @Test
    public void threeLevelInnerMergeTransactionTest() throws Exception {
        testUtils.persistTemporalStudent();
        final StudentEntity student = testUtils.loadAllStudents().get(0);

        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
            student.setName("First Layer");
            student.setFamily("Should Commit");
            repository.merge(student);
            TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) status -> {
                student.setName("Second Layer");
                student.setFamily("Should Rollback");
                repository.merge(student);
                TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) innerStatus -> {
                    student.setName("Third Layer");
                    student.setFamily("Should Rollback");
                    repository.merge(student);
                });
            });
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity loadedStudent = studentList.get(0);
        assertEquals("First Layer", loadedStudent.getName());
        assertEquals("Should Commit", loadedStudent.getFamily());
    }

    @Test
    public void reverseThreeLevelInnerMergeTransactionTest() throws Exception {
        testUtils.persistTemporalStudent();
        final StudentEntity student = testUtils.loadAllStudents().get(0);

        TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) outerStatus -> {
            student.setName("First Layer");
            student.setFamily("Should Rollback");
            repository.merge(student);
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> {
                student.setName("Second Layer");
                student.setFamily("Should Commit");
                repository.merge(student);
                TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) innerStatus -> {
                    student.setName("Third Layer");
                    student.setFamily("Should Rollback");
                    repository.merge(student);
                });
            });
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity loadedStudent = studentList.get(0);
        assertEquals("Second Layer", loadedStudent.getName());
        assertEquals("Should Commit", loadedStudent.getFamily());
    }

    @Ignore("Nested Transaction is not supported")
    @Test
    public void twoLevelInnerNestedTransactionWithCheckedExceptionTest() throws Exception {
        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
            repository.persist(new StudentEntity(1L, "First Layer", "Should Commit", 32));
            try {
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> {
                    repository.persist(new StudentEntity(10L, "First Nested of Second Layer", "Rollback", 10));
                    throw new Exception();
                });
            } catch (Exception e) {
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> {
                    repository.persist(new StudentEntity(2L, "Second Nested of Second Layer", "Should Commit", 32));
                });
            }
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(2, studentList.size());
        studentList.forEach(student -> assertEquals("Should Commit", student.getFamily()));
    }

    @Ignore("Nested Transaction is not supported")
    @Test
    public void twoLevelInnerNestedTransactionWithDataAccessExceptionTest() throws Exception {
        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
            repository.persist(new StudentEntity(1L, "First Layer", "Should Commit", 32));
            try {
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> {
                    repository.persist(new StudentEntity(1L, "First Nested of Second Layer", "Should Rollback", 10));
                });
            } catch (Exception e) {
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> {
                    repository.persist(new StudentEntity(2L, "Second Nested of Second Layer", "Should Commit", 32));
                });
            }
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(2, studentList.size());
        studentList.forEach(student -> assertEquals("Should Commit", student.getFamily()));
    }

    @Ignore("Nested Transaction is not supported")
    @Test(expected = TransactionException.class)
    public void twoLevelNestedOptimisticLockingTest() throws Exception {
        testUtils.persistTemporalStudent();

        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
            final StudentEntity student = testUtils.loadAllStudents().get(0);
            student.setName("First Layer");
            student.setFamily("Should Commit");
            repository.merge(student);
            try {
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> {
                    student.setName("First Nested of Second Layer");
                    student.setFamily("Should Rollback");
                    repository.merge(student);
                    throw new Exception();
                });
            } catch (Exception e) {
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> {
                    student.setName("Second Nested of Second Layer");
                    student.setFamily("Should Commit");
                    repository.merge(student);
                });
            }
        });
    }

    @Ignore("Nested Transaction is not supported")
    @Test
    public void twoLevelInnerMergeNestedTransactionTest() throws Exception {
        testUtils.persistTwoTemporalStudents();

        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
            final List<StudentEntity> studentList = testUtils.loadAllStudents();
            final StudentEntity firstStudent = studentList.get(0);
            final StudentEntity secondStudent = studentList.get(1);

            firstStudent.setName("First Layer");
            firstStudent.setFamily("Should Commit");
            repository.merge(firstStudent);
            secondStudent.setName("First Layer");
            secondStudent.setFamily("Should Commit");
            repository.merge(secondStudent);

            try {
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> {
                    firstStudent.setName("First Nested of Second Layer");
                    firstStudent.setFamily("Should Rollback");
                    repository.merge(firstStudent);
                    throw new Exception();
                });
            } catch (Exception e) {
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> {
                    secondStudent.setName("Second Nested of Second Layer");
                    secondStudent.setFamily("Should Commit");
                    repository.merge(secondStudent);
                });
            }
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(2, studentList.size());
        final StudentEntity firstStudent = studentList.get(0);
        final StudentEntity secondStudent = studentList.get(1);
        assertEquals("First Layer", firstStudent.getName());
        assertEquals("Should Commit", firstStudent.getFamily());
        assertEquals("Second Nested of Second Layer", secondStudent.getName());
        assertEquals("Should Commit", secondStudent.getFamily());
    }

    @Ignore("Nested Transaction is not supported")
    @Test(expected = TransactionException.class)
    public void threeLevelNestedReadOnlyTransactionTest() throws Exception {
        testUtils.persistTemporalStudent();

        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
            final StudentEntity student = testUtils.loadAllStudents().get(0);
            student.setName("First Layer");
            student.setFamily("Should Commit");
            repository.merge(student);
            TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) status -> {
                student.setName("Second Layer");
                student.setFamily("Should Rollback");
                repository.merge(student);
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) innerStatus -> {
                    student.setName("Third Layer");
                    student.setFamily("Should Rollback");
                    repository.merge(student);
                });
            });
        });
    }

    @Ignore("Nested Transaction is not supported")
    @Test
    public void threeLevelInnerMergeNestedTransactionTest() throws Exception {
        testUtils.persistTemporalStudent();

        TransactionUtils.executeTransactionally(REQUIRED, (TransactionCallbackWithoutResult) outerStatus -> {
            final StudentEntity student = testUtils.loadAllStudents().get(0);
            student.setName("First Layer");
            student.setFamily("Should Commit");
            repository.merge(student);
            TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) status -> {
                student.setName("Second Layer");
                student.setFamily("Should Commit");
                repository.merge(student);
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) innerStatus -> {
                    student.setName("Third Layer");
                    student.setFamily("Should Commit");
                    repository.merge(student);
                });
            });
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity loadedStudent = studentList.get(0);
        assertEquals("Third Layer", loadedStudent.getName());
        assertEquals("Should Commit", loadedStudent.getFamily());
    }

    @Ignore("Nested Transaction is not supported")
    @Test
    public void reverseThreeLevelInnerMergeNestedTransactionTest() throws Exception {
        testUtils.persistTemporalStudent();

        TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) outerStatus -> {
            final StudentEntity student = testUtils.loadAllStudents().get(0);
            student.setName("First Layer");
            student.setFamily("Should Rollback");
            repository.merge(student);
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> {
                final StudentEntity loadedStudent = testUtils.loadAllStudents().get(0);
                loadedStudent.setName("Second Layer");
                loadedStudent.setFamily("Should Commit");
                repository.merge(loadedStudent);
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) innerStatus -> {
                    loadedStudent.setName("Third Layer");
                    loadedStudent.setFamily("Should Commit");
                    repository.merge(loadedStudent);
                });
            });
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(1, studentList.size());
        final StudentEntity loadedStudent = studentList.get(0);
        assertEquals("Third Layer", loadedStudent.getName());
        assertEquals("Should Commit", loadedStudent.getFamily());
    }

    @Ignore("Nested Transaction is not supported")
    @Test
    public void reverseThreeLevelInnerNestedTransactionTest() throws Exception {
        TransactionUtils.executeReadOnly((TransactionCallbackWithoutResult) outerStatus -> {
            repository.persist(new StudentEntity(10L, "First Layer", "Should Rollback", 10));
            TransactionUtils.executeTransactionally(REQUIRES_NEW, (TransactionCallbackWithoutResult) status -> {
                repository.persist(new StudentEntity(1L, "Second Layer", "Should Commit", 10));
                TransactionUtils.executeTransactionally(NESTED, (TransactionCallbackWithoutResult) innerStatus -> {
                    repository.persist(new StudentEntity(2L, "Third Layer", "Should Commit", 10));
                });
            });
        });

        final List<StudentEntity> studentList = testUtils.loadAllStudents();
        assertNotNull(studentList);
        assertEquals(2, studentList.size());
        studentList.forEach(student -> assertEquals("Should Commit", student.getFamily()));
    }
}
