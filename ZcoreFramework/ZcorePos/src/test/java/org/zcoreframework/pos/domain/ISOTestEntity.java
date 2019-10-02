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
@Table(name = "ISO_TEST", schema = ConstantsUtils.Schema.ZC)
public class ISOTestEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "ISO_TEST_SEQ_GEN", schema = ConstantsUtils.Schema.ZC, sequenceName = "ISO_TEST_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ISO_TEST_SEQ_GEN")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Lob
    @Column(name = "CONTENT")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
