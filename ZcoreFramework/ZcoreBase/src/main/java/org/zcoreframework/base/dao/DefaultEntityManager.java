/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DefaultEntityManager {

	@PersistenceContext(unitName = "zcore")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
