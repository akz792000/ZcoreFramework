/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.data.base;

import java.util.Map;

import javax.persistence.LockModeType;

import org.zcoreframework.base.data.Repository;

public interface DefaultJpaRepository extends Repository {

	<T> void persist(T entity);

	<T> T merge(T entity);

	<T> void remove(T entity);

	<T, ID> T find(Class<T> clazz, ID id);

	<T, ID> T find(Class<T> clazz, ID id, Map<String, Object> properties);

	<T, ID> T find(Class<T> clazz, ID id, LockModeType lockMode);

	<T, ID> T find(Class<T> clazz, ID id, LockModeType lockMode, Map<String, Object> properties);

	void flush();

	<T> void lock(T entity, LockModeType lockMode);

	<T> void lock(T entity, LockModeType lockMode, Map<String, Object> properties);

	<T> void refresh(T entity);

	<T> void refresh(T entity, Map<String, Object> properties);

	<T> void refresh(T entity, LockModeType lockMode);

	<T> void refresh(T entity, LockModeType lockMode, Map<String, Object> properties);

	<T> void detach(T entity);

	<T> boolean contains(T entity);

	<T> LockModeType getLockMode(T entity);

	void setProperty(String propertyName, Object value);

	Map<String, Object> getProperties();

}
