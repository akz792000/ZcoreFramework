/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map.plain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zcoreframework.base.util.PlainMap;
import org.zcoreframework.component.entryset.EntrySet;
import org.zcoreframework.component.map.AbstractGenericMapImpl;
import org.zcoreframework.component.map.BaseMapEntityModel;

public class PlainGenericMapEntity extends AbstractGenericMapImpl<PlainGenericMapEntityDAOImpl, BaseMapEntityModel, PlainMap> {

	@Override
	public List<EntrySet> getEntrySet(Object key) {
		List<EntrySet> list = new ArrayList<>();
		if (key != null) {
			list.add((EntrySet) getItem().get(key));
		} else {
			for (Map.Entry<Object, Object> entry : getItem().entrySet()) {
				list.add((EntrySet) entry.getValue());
			}
		}
		return list;
	}
			
}
