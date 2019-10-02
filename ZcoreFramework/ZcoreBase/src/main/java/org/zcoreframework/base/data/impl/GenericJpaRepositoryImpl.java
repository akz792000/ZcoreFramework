package org.zcoreframework.base.data.impl;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.LockModeType;

import org.zcoreframework.base.dao.DefaultEntityManager;
import org.zcoreframework.base.data.base.GenericJpaRepository;

public class GenericJpaRepositoryImpl<T, ID extends Serializable> extends DefaultEntityManager implements GenericJpaRepository<T, ID> {

	private Class<T> entityClass;

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public GenericJpaRepositoryImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
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
	public T find(ID id) {
		return getEntityManager().find(getEntityClass(), id);
	}

	@Override
	public T find(ID id, Map<String, Object> properties) {
		return getEntityManager().find(getEntityClass(), id, properties);
	}

	@Override
	public T find(ID id, LockModeType lockMode) {
		return getEntityManager().find(getEntityClass(), id, lockMode);
	}

	@Override
	public T find(ID id, LockModeType lockMode, Map<String, Object> properties) {
		return getEntityManager().find(getEntityClass(), id, lockMode, properties);
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}

	@Override
	public void lock(T entity, LockModeType lockMode) {
		getEntityManager().lock(entity, lockMode);

	}

	@Override
	public void lock(T entity, LockModeType lockMode, Map<String, Object> properties) {
		getEntityManager().lock(entity, lockMode, properties);
	}

	@Override
	public void refresh(T entity) {
		getEntityManager().refresh(entity);
	}

	@Override
	public void refresh(T entity, Map<String, Object> properties) {
		getEntityManager().refresh(entity, properties);
	}

	@Override
	public void refresh(T entity, LockModeType lockMode) {
		getEntityManager().refresh(entity, lockMode);
	}

	@Override
	public void refresh(T entity, LockModeType lockMode, Map<String, Object> properties) {
		getEntityManager().refresh(entity, lockMode, properties);
	}

	@Override
	public void detach(T entity) {
		getEntityManager().detach(entity);
	}

	@Override
	public boolean contains(T entity) {
		return getEntityManager().contains(entity);
	}

	@Override
	public LockModeType getLockMode(T entity) {
		return getEntityManager().getLockMode(entity);
	}

	@Override
	public void setProperty(String propertyName, Object value) {
		getEntityManager().setProperty(propertyName, value);
	}

	@Override
	public Map<String, Object> getProperties() {
		return getEntityManager().getProperties();
	}

}
