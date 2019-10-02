/**
 *
 * @author Ali Karimizandi
 * @since 2009
 *
 */

package org.zcoreframework.security.domain;

import org.zcoreframework.base.domain.BaseEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditEventListener.class)
public abstract class AuditEntity extends BaseEntity {

    private static final long serialVersionUID = 2L;

	@Column(name = AuditEventListener.INSERT_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date insertDate;

	@Column(name = AuditEventListener.INSERT_USER_ID, precision = 12)
	private Long insertUserId;

	@Column(name = AuditEventListener.INSERT_ORGANIZATION_ID, precision = 12)
	private Long insertOrganizationId;

	@Column(name = AuditEventListener.MODIFY_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;

	@Column(name = AuditEventListener.MODIFY_USER_ID, precision = 12)
	private Long modifyUserId;

	@Column(name = AuditEventListener.MODIFY_ORGANIZATION_ID, precision = 12)
	private Long modifyOrganizationId;

	/**
	 * private Setter and  @XmlAttribute added  to convert Java objects into XML structures
	 */
	@XmlAttribute
	public Date getInsertDate() {
		return insertDate;
	}

	@XmlAttribute
	public Long getInsertUserId() {
		return insertUserId;
	}

	@XmlAttribute
	public Long getInsertOrganizationId() {
		return insertOrganizationId;
	}

	@XmlAttribute
	public Date getModifyDate() {
		return modifyDate;
	}

	@XmlAttribute
	public Long getModifyUserId() {
		return modifyUserId;
	}

	@XmlAttribute
	public Long getModifyOrganizationId() {
		return modifyOrganizationId;
	}

	protected void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	protected void setInsertUserId(Long insertUserId) {
		this.insertUserId = insertUserId;
	}

	protected void setInsertOrganizationId(Long insertOrganizationId) {
		this.insertOrganizationId = insertOrganizationId;
	}

	protected void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	protected void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	protected void setModifyOrganizationId(Long modifyOrganizationId) {
		this.modifyOrganizationId = modifyOrganizationId;
	}
}