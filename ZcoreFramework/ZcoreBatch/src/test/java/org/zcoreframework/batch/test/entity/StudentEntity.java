package org.zcoreframework.batch.test.entity;

import org.zcoreframework.base.domain.BaseEntity;
import org.zcoreframework.base.util.ConstantsUtils.Schema;

import javax.persistence.*;

/**
 * Created by Pooya Hosseini on 4/10/2016.
 *
 * @author pooya
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 */

@Entity
@Table(name = StudentEntity.TABLE_NAME, schema = Schema.ZC)
@NamedQueries({
        @NamedQuery(name = "selectAll", query = "select e from StudentEntity e"),
        @NamedQuery(name = "deleteAll", query = "delete from StudentEntity e"),
        @NamedQuery(name = "selectByRange", query = "select e from StudentEntity e where e.id >= :f and e.id < :t "),
        @NamedQuery(name = "zcore.batch.selectGreaterThan", query = "select e from StudentEntity e where (e.id > :f) order by e.id"),
        @NamedQuery(name = "zcore.batch.selectByRangeInclusive", query = "select e from StudentEntity e where (e.id between :f and :t) order by e.id")
})
public class StudentEntity extends BaseEntity {

    public static final String TABLE_NAME = "TST_STUDENT";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_SEQ";
    public static final String SEQUENCE_GENERATOR_NAME = TABLE_NAME + "_SEQUENCE";

    @Id
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, schema = Schema.ZC, sequenceName = SEQUENCE_NAME, initialValue = 10000001, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    private Long id;

    private String name;

    private String family;

    private Integer age;

    public StudentEntity() {
    }

    public StudentEntity(Long id, String name, String family, Integer age) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", age=" + age +
                '}';
    }
}
