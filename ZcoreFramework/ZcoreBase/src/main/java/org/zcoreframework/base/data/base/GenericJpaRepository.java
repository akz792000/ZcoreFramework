/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.data.base;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.LockModeType;

import org.zcoreframework.base.data.Repository;

public interface GenericJpaRepository<T, ID extends Serializable> extends Repository {

	void persist(T entity);

	T merge(T entity);

	void remove(T entity);

	T find(ID id);

	T find(ID id, Map<String, Object> properties);

	T find(ID id, LockModeType lockMode);

	T find(ID id, LockModeType lockMode, Map<String, Object> properties);

	void flush();

	void lock(T entity, LockModeType lockMode);

	void lock(T entity, LockModeType lockMode, Map<String, Object> properties);

	void refresh(T entity);

	void refresh(T entity, Map<String, Object> properties);

	void refresh(T entity, LockModeType lockMode);

	void refresh(T entity, LockModeType lockMode, Map<String, Object> properties);

	void detach(T entity);

	boolean contains(T entity);

	LockModeType getLockMode(T entity);

	void setProperty(String propertyName, Object value);

	Map<String, Object> getProperties();

}
