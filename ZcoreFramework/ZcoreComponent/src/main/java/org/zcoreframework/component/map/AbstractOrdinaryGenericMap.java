/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map;

import java.util.List;

import org.zcoreframework.component.entryset.EntrySet;
import org.zcoreframework.component.listvalue.ListValue;
import org.zcoreframework.component.listvalue.ListValueUtils;
import org.zcoreframework.component.map.GenericMap;

public abstract class AbstractOrdinaryGenericMap<E> implements GenericMap<E> {

	private E item;

	public AbstractOrdinaryGenericMap(E item) {
		this.item = item;
	}

	@Override
	public E getItem() {
		return item;
	}

	@Override
	public void setItem(E item) {
		this.item = item;
	}
	
	protected abstract List<EntrySet> getResult(Object key);

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
		return ListValueUtils.valueOf(getResult(key), method, expression);
	}

}