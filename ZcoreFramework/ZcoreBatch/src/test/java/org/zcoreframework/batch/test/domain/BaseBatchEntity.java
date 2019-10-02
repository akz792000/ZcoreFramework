package org.zcoreframework.batch.test.domain;

import org.zcoreframework.base.util.ConstantsUtils.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by z.azadi on 5/1/2016.
 */
@Entity
@Table(name = BaseBatchEntity.TABLE_NAME, schema = Schema.ZC)
@NamedNativeQueries({
        @NamedNativeQuery(name = "BaseBatchEntity.loadActive1", query = "select c.* from ZC.TST_BASE_BATCH c   order by c.id  ", resultClass = BaseBatchEntity.class),
        @NamedNativeQuery(name = "BaseBatchEntity.loadActive2", query = "select c.* from ZC.TST_BASE_BATCH c where c.id>5 ", resultClass = BaseBatchEntity.class)
})
@NamedQueries({
        @NamedQuery(name = "BaseBatchEntity.selectByRange", query = "select e from BaseBatchEntity e where e.contractId >= :f and e.contractId < :t")
})
public class BaseBatchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "TST_BASE_BATCH";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_SEQ";
    public static final String SEQUENCE_GENERATOR_NAME = TABLE_NAME + "_SEQUENCE";

    @Id
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, schema = Schema.ZC, sequenceName = SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONTRACT_ID", precision = 18)

    private Long contractId;

    @Column(name = "ORG_ID", precision = 18)
    @NotNull
    private Long orgId;

    @Column(name = "STATUS", precision = 18)
    @NotNull
    private Long STATUS;

    @Column(name = "SAMPLE", precision = 18)
    private String sample;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Long STATUS) {
        this.STATUS = STATUS;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public String toString() {
        return "BaseBatchEntity{" +
                "id=" + id +
                ", contractId=" + contractId +
                ", orgId=" + orgId +
                ", STATUS=" + STATUS +
                ", sample='" + sample + '\'' +
                '}';
    }


}

