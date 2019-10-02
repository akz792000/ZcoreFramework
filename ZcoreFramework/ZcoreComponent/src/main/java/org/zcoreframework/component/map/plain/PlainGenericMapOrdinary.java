/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map.plain;

import java.util.List;

import org.zcoreframework.base.util.PlainMap;
import org.zcoreframework.component.entryset.EntrySet;
import org.zcoreframework.component.map.AbstractOrdinaryGenericMap;

public class PlainGenericMapOrdinary extends AbstractOrdinaryGenericMap<PlainMap> {

	public PlainGenericMapOrdinary(PlainMap item) {
		super(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntrySet> getResult(Object key) {
		return (List<EntrySet>) getItem().get(key);
	}

}