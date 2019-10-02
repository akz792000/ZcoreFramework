/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.security.domain;

import java.util.Calendar;

import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.eclipse.persistence.sessions.Record;
import org.zcoreframework.security.core.SecurityHelper;
import org.zcoreframework.security.model.UserInfo;

public class AuditEventListener extends DescriptorEventAdapter {

    public static final String INSERT_DATE = "INSERT_DATE";

    public static final String INSERT_USER_ID = "INSERT_USER_ID";

    public static final String INSERT_ORGANIZATION_ID = "INSERT_ORGANIZATION_ID";

    public static final String MODIFY_DATE = "MODIFY_DATE";

    public static final String MODIFY_USER_ID = "MODIFY_USER_ID";

    public static final String MODIFY_ORGANIZATION_ID = "MODIFY_ORGANIZATION_ID";

    protected AuditModel getAuditModel() {
        return new AuditModel(Calendar.getInstance().getTime(), SecurityHelper.getUserInfo());
    }

    @SuppressWarnings("unchecked")
    protected AuditModel put(DescriptorEvent event, String date, String userId, String organizationId) {
        // get record and table name
        Record record = event.getRecord();
        String table = event.getDescriptor().getTableName();

        AuditModel model = getAuditModel();

        // set date
        record.put(table + "." + date, model.getDate());

        // set user id
        record.put(table + "." + userId, model.getUserId());

        // set organization id
        record.put(table + "." + organizationId, model.getOrganizationId());

        return model;
    }

    protected void fillInsertFields(AuditEntity entity, AuditModel model) {
        entity.setInsertDate(model.getDate());
        entity.setInsertUserId(model.getUserId());
        entity.setInsertOrganizationId(model.getOrganizationId());
    }

    protected void fillModifyFields(AuditEntity entity, AuditModel model) {
        entity.setModifyDate(model.getDate());
        entity.setModifyUserId(model.getUserId());
        entity.setModifyOrganizationId(model.getOrganizationId());
    }

    public void aboutToInsert(DescriptorEvent event) {
        fillInsertFields((AuditEntity) event.getSource(), put(event, INSERT_DATE, INSERT_USER_ID, INSERT_ORGANIZATION_ID));
    }

    public void aboutToUpdate(DescriptorEvent event) {
        fillModifyFields((AuditEntity) event.getSource(), put(event, MODIFY_DATE, MODIFY_USER_ID, MODIFY_ORGANIZATION_ID));
    }

    public void aboutToDelete(DescriptorEvent event) {
        fillModifyFields((AuditEntity) event.getSource(), put(event, MODIFY_DATE, MODIFY_USER_ID, MODIFY_ORGANIZATION_ID));
    }

}
