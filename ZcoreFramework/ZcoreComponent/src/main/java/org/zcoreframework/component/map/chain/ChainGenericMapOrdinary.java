/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map.chain;

import java.util.List;

import org.zcoreframework.base.util.ChainMap;
import org.zcoreframework.component.entryset.EntrySet;
import org.zcoreframework.component.map.AbstractOrdinaryGenericMap;

public class ChainGenericMapOrdinary extends AbstractOrdinaryGenericMap<ChainMap> {

	public ChainGenericMapOrdinary(ChainMap item) {
		super(item);
	}

	@Override
	public List<EntrySet> getResult(Object key) {
		return getItem().getByGroupList(key);
	}

}