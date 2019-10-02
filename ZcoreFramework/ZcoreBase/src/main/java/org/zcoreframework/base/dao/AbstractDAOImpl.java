/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractDAOImpl<T extends Serializable> extends DefaultEntityManager implements GenericInterfaceDAO<T> {

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractDAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		setEntityClass((Class<T>) genericSuperclass.getActualTypeArguments()[0]);
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public Long loadSequence() {
		return ((BigDecimal) getEntityManager().createNamedQuery("loadSequence").getSingleResult()).longValue();
	}

	@Override
	public T loadById(Long id) {
		return getEntityManager().find(entityClass, id);
	}

	@Override
	public T loadByIdLockMode(Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("javax.persistence.lock.timeout", 0);
		return getEntityManager().find(entityClass, id, LockModeType.PESSIMISTIC_WRITE, map);
	}

	@SuppressWarnings("unchecked")
	protected T loadCompletelyEntity(String namedQuery, Object value) {
		Query query = getEntityManager().createNamedQuery(namedQuery);
		List<T> lists = query.setParameter("value", value).getResultList();
		return lists.size() == 0 ? null : lists.get(0);
	}

	@Override
	public List<T> loadCompletely() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> rootEntry = cq.from(entityClass);
		CriteriaQuery<T> all = cq.select(rootEntry);
		TypedQuery<T> allQuery = getEntityManager().createQuery(all);
		return allQuery.getResultList();
	}

	@Override
	public T loadCompletely(Object value) {
		return loadCompletelyEntity(entityClass.getSimpleName() + ".loadCompletely", value);
	}

	@Override
	public void delete(Long id) {
		Query query = getEntityManager().createQuery("delete from " + entityClass.getSimpleName() + " u where u.id = :value");
		query.setParameter("value", id).executeUpdate();
	}

	@Override
	public void persist(T entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	@Override
	public void remove(T entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public void refresh(T entity) {
		getEntityManager().refresh(entity);
	}

	@Override
	public void detach(T entity) {
		getEntityManager().detach(entity);
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}

}