package org.zcoreframework.batch.util;

import org.eclipse.persistence.internal.sessions.UnitOfWorkImpl;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.zcoreframework.base.dao.DefaultEntityManager;
import org.zcoreframework.base.util.ApplicationContextUtils;

import javax.persistence.EntityManager;
import java.sql.Connection;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */
public class EntityManagerUtils {

    static EntityManager getEntityManager() {
        return ApplicationContextUtils.createBean(DefaultEntityManager.class).getEntityManager();
    }

    static Connection getActiveConnection(EntityManager entityManager) {
        return ((UnitOfWorkImpl) ((JpaEntityManager) entityManager).getActiveSession().acquireUnitOfWork()).getAccessor().getConnection();
    }

    static Connection getActiveConnection() {
        return getActiveConnection(getEntityManager());
    }
}
