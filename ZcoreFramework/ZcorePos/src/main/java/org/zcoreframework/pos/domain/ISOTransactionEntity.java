package org.zcoreframework.pos.domain;

import org.zcoreframework.base.domain.BaseEntity;
import org.zcoreframework.base.util.ConstantsUtils;
import org.zcoreframework.pos.constant.TransactionStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = ISOTransactionEntity.TABLE_NAME, schema = ConstantsUtils.Schema.ZC)
@NamedQueries({@NamedQuery(name = ISOTransactionEntity.SELECT_SUBMITTED, query =
        "select e from ISOTransactionEntity e where e.isoMti = :isoMti and e.isoTrace = :isoTrace " +
                "and e.isoTxDate = :isoTxDate and e.isoAcqCode = :isoAcqCode and e.isoAccId = :isoAccId")})
public class ISOTransactionEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    public final static String SELECT_SUBMITTED = "zcore.pos.selectSubmitted";
    public final static String TABLE_NAME = "ISO_TRANSACTION";
    public final static String SEQUENCE_NAME = TABLE_NAME + "_SEQ";
    public final static String SEQUENCE_GEN_NAME = TABLE_NAME + "_SEQ_GEN";

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = SEQUENCE_GEN_NAME, schema = ConstantsUtils.Schema.ZC, sequenceName = SEQUENCE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GEN_NAME)
    private Long id;

    @Column(name = "ISO_MTI")
    private String isoMti;

    @Column(name = "ISO_TRACE")
    private String isoTrace;

    @Column(name = "ISO_TX_DATE")
    private String isoTxDate;

    @Column(name = "ISO_ACQ_CODE")
    private String isoAcqCode;

    @Column(name = "ISO_ACC_ID")
    private String isoAccId;

    @Column(name = "IN_MSG_ID")
    private Long inMsgId;

    @Column(name = "OUT_MSG_ID")
    private Long outMsgId;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "EXPIRE_DATE")
    private String expireDate;

    @Column(name = "ACTION_CODE")
    private String actionCode;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    public static String getSelectSubmitted() {
        return SELECT_SUBMITTED;
    }

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

    public String getIsoMti() {
        return isoMti;
    }

    public void setIsoMti(String isoMti) {
        this.isoMti = isoMti;
    }

    public String getIsoTrace() {
        return isoTrace;
    }

    public void setIsoTrace(String isoTrace) {
        this.isoTrace = isoTrace;
    }

    public String getIsoTxDate() {
        return isoTxDate;
    }

    public void setIsoTxDate(String isoTxDate) {
        this.isoTxDate = isoTxDate;
    }

    public String getIsoAcqCode() {
        return isoAcqCode;
    }

    public void setIsoAcqCode(String isoAcqCode) {
        this.isoAcqCode = isoAcqCode;
    }

    public String getIsoAccId() {
        return isoAccId;
    }

    public void setIsoAccId(String isoAccId) {
        this.isoAccId = isoAccId;
    }

    public Long getInMsgId() {
        return inMsgId;
    }

    public void setInMsgId(Long inMsgId) {
        this.inMsgId = inMsgId;
    }

    public Long getOutMsgId() {
        return outMsgId;
    }

    public void setOutMsgId(Long outMsgId) {
        this.outMsgId = outMsgId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setErrorComment(Throwable th) {
        if (th == null) {
            this.comment = null;
            return;
        } else if (th.getCause() != null) {
            th = th.getCause();
        }

        this.comment = th.getClass().getName() + " - " + th.getMessage();
    }
}
