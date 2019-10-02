package org.zcoreframework.batch.domain;

import org.zcoreframework.base.util.ConstantsUtils.Schema;

import javax.persistence.*;

/**
 * Created by z.azadi on 5/1/2016.
 */
@Entity
@Table(name = StepItemsLogEntity.TABLE_NAME, schema = Schema.BH)
public class StepItemsLogEntity {

    public static final String TABLE_NAME = "BATCH_STEP_ITEMS_LOG";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_SEQ";
    public static final String SEQUENCE_GENERATOR_NAME = TABLE_NAME + "_SEQUENCE";

    @Column(name = "ID")
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, schema = Schema.BH, sequenceName = SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEP_LOG_ID", referencedColumnName = "ID", nullable = false)
    private StepLogEntity stepLogEntity;

    @Column(name = "ITEM", length = 4000)
    private String item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StepLogEntity getStepLogEntity() {
        return stepLogEntity;
    }

    public void setStepLogEntity(StepLogEntity stepLogEntity) {
        this.stepLogEntity = stepLogEntity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
