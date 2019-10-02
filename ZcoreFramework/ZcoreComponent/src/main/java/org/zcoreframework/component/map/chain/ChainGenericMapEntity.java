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
import org.zcoreframework.component.map.AbstractGenericMapImpl;

public class ChainGenericMapEntity extends AbstractGenericMapImpl<ChainGenericMapEntityDAOImpl, ChainGenericMapEntityModel, ChainMap> {
	
	@Override
	public List<EntrySet> getEntrySet(Object key) {
		return getItem().getByGroupList(key);
	}
	
}
