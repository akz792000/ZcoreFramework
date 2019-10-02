/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zcoreframework.base.component.AbstractPyramidComponentImpl;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.component.entryset.EntrySet;
import org.zcoreframework.component.listvalue.ListValue;
import org.zcoreframework.component.listvalue.ListValueUtils;

public abstract class AbstractGenericMapImpl<D extends AbstractPyramidDAOImpl<M>, M, E> extends AbstractPyramidComponentImpl<D, M> implements GenericMap<E> {
	
	private static final Log log = LogFactory.getLog(AbstractGenericMapImpl.class);	
	
	private E item;
	
	@Override
	public E getItem() {
		return item;
	}

	@Override
	public void setItem(E item) {
		this.item = item;
	}
			
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		if (item == null) { 
			if (log.isDebugEnabled()) {
				log.debug("key code initialized.");
			}
			item = ((GenericMapDAO<M, E>) getDao()).getResult();			
		}
	}
	
	public abstract List<EntrySet> getEntrySet(Object key);
	
	@Override
	public ListValue listValue(Object key) {
		return listValue(key, null, null);
	}
	
	@Override
	public ListValue listValue(Object key, String method) {
		return listValue(key, method, null);
	}
	
	@Override
	public ListValue listValue(Object key, String method, String expression) {
		return ListValueUtils.valueOf(getEntrySet(key), method, expression);
	}		
	
}
