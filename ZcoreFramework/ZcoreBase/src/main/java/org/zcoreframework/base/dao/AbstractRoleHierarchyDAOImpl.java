/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Query;

import org.zcoreframework.base.model.AuthorityItemModel;

public abstract class AbstractRoleHierarchyDAOImpl<T extends Serializable> extends DefaultEntityManager {
	
	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractRoleHierarchyDAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	public List<AuthorityItemModel> loadAll() {
		Query query = getEntityManager().createQuery("select new org.zcoreframework.base.model.AuthorityItemModel(a.id, a.name, a.parent, a.seq) from " + entityClass.getName() + " a order by a.parent, a.seq");
		return (List<AuthorityItemModel>) query.getResultList();	
	}

}
