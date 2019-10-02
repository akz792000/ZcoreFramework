/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.mapping.source.mapper;

import java.util.ArrayList;
import java.util.List;

import org.zcoreframework.component.entryset.EntrySetSimple;
import org.zcoreframework.component.listvalue.ListValue;
import org.zcoreframework.component.mapping.MapperModel;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.mapper.ModelFieldBeanMapperImpl;

public class ListValueMapper extends ModelFieldBeanMapperImpl<ListValue, MapperModel> {

	public ListValueMapper(FieldBeanWrapper mappingBeanWrapper, MapperModel model) {
		super(mappingBeanWrapper, model);
	}

	@Override
	public Object getPropertyValue() throws Exception {		
		List<EntrySetSimple> selectedList = getValue().getItem().getSelected();
		if (selectedList != null) {
			List<Object> result = new ArrayList<>();
			for (EntrySetSimple selected : selectedList) {
				result.add(selected.getValue());
			}
			return result.toArray();
		}
		return null;
	}
	
	@Override
	public void setPropertyValue(Object object) throws Exception {
		getValue().getItem().setSelectedItem(object);
	}

}
