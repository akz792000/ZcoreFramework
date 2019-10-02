/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericInterfaceDAO<T extends Serializable> {
	
	Long loadSequence();
	
	T loadById(Long id);
	
	T loadByIdLockMode(Long id);
	
	T loadCompletely(Object value);
	
	List<T> loadCompletely();
	
	void persist(T entity);
	
	T merge(T entity);
	
	void delete(Long id);
	
	void remove(T entity);
	
	void refresh(T entity);
		
	void detach(T entity);
	
	void flush();

}
