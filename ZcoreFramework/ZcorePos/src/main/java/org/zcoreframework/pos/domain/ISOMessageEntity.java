package org.zcoreframework.pos.domain;

import org.zcoreframework.base.domain.BaseEntity;
import org.zcoreframework.base.util.ConstantsUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = ISOMessageEntity.TABLE_NAME, schema = ConstantsUtils.Schema.ZC)
public class ISOMessageEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    public final static String TABLE_NAME = "ISO_MESSAGE";
    public final static String SEQUENCE_NAME = TABLE_NAME + "_SEQ";
    public final static String SEQUENCE_GEN_NAME = TABLE_NAME + "_SEQ_GEN";

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = SEQUENCE_GEN_NAME, schema = ConstantsUtils.Schema.ZC, sequenceName = SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GEN_NAME)
    private Long id;

    @Column(name = "IS_INCOME")
    private Boolean isIncome;

    @Column(name = "IS_DELIVERED")
    private Boolean isDelivered;

    @Lob
    @Column(name = "CONTENT")
    private String content;

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public static String getSequenceGenName() {
        return SEQUENCE_GEN_NAME;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIncome() {
        return isIncome;
    }

    public void setIncome(Boolean income) {
        isIncome = income;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public void setDelivered(Boolean delivered) {
        isDelivered = delivered;
    }
}
