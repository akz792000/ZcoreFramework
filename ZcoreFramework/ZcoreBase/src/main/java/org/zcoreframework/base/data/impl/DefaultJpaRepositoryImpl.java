package org.zcoreframework.base.data.impl;

import java.util.Map;

import javax.persistence.LockModeType;

import org.zcoreframework.base.dao.DefaultEntityManager;
import org.zcoreframework.base.data.base.DefaultJpaRepository;

public class DefaultJpaRepositoryImpl extends DefaultEntityManager implements DefaultJpaRepository {

	@Override
	public <T> void persist(T entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public <T> T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	@Override
	public <T> void remove(T entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public <T, ID> T find(Class<T> clazz, ID id) {
		return getEntityManager().find(clazz, id);
	}

	@Override
	public <T, ID> T find(Class<T> clazz, ID id, Map<String, Object> properties) {
		return getEntityManager().find(clazz, id, properties);
	}

	@Override
	public <T, ID> T find(Class<T> clazz, ID id, LockModeType lockMode) {
		return getEntityManager().find(clazz, id, lockMode);
	}

	@Override
	public <T, ID> T find(Class<T> clazz, ID id, LockModeType lockMode, Map<String, Object> properties) {
		return getEntityManager().find(clazz, id, lockMode, properties);
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}

	@Override
	public <T> void lock(T entity, LockModeType lockMode) {
		getEntityManager().lock(entity, lockMode);

	}

	@Override
	public <T> void lock(T entity, LockModeType lockMode, Map<String, Object> properties) {
		getEntityManager().lock(entity, lockMode, properties);
	}

	@Override
	public <T> void refresh(T entity) {
		getEntityManager().refresh(entity);
	}

	@Override
	public <T> void refresh(T entity, Map<String, Object> properties) {
		getEntityManager().refresh(entity, properties);
	}

	@Override
	public <T> void refresh(T entity, LockModeType lockMode) {
		getEntityManager().refresh(entity, lockMode);
	}

	@Override
	public <T> void refresh(T entity, LockModeType lockMode, Map<String, Object> properties) {
		getEntityManager().refresh(entity, lockMode, properties);
	}

	@Override
	public <T> void detach(T entity) {
		getEntityManager().detach(entity);
	}

	@Override
	public <T> boolean contains(T entity) {
		return getEntityManager().contains(entity);
	}

	@Override
	public <T> LockModeType getLockMode(T entity) {
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
