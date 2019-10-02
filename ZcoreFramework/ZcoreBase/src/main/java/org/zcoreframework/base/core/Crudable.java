/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.core;

import java.util.Map;

public interface Crudable<E> {
	
	public void persist(E entity, Map<String, Object> selected) throws Exception;
	
	public void merge(E entity, Map<String, Object> selected) throws Exception;
	
	public Boolean remove(E entity) throws Exception;
	
	public void commit() throws Exception;
	
}
