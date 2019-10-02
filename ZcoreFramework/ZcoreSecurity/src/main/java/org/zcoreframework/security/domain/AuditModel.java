package org.zcoreframework.security.domain;

import org.zcoreframework.security.model.UserInfo;

import java.util.Date;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 *         Date 11/18/2017.
 */
public class AuditModel {

    private final Date date;

    private final Long userId;

    private final Long organizationId;

    public AuditModel(Date date, UserInfo userInfo) {
        this.date = date;
        if (userInfo != null) {
            this.userId = userInfo.getId();
            this.organizationId = userInfo.getOrganization().getId();
        } else {
            this.userId = null;
            this.organizationId = null;
        }
    }

    public Date getDate() {
        return date;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }
}
