/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.mapping.source.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.zcoreframework.component.lovbox.LovBox;
import org.zcoreframework.component.mapping.MapperModel;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.mapper.ModelFieldBeanMapperImpl;

public class LovBoxMapper extends ModelFieldBeanMapperImpl<LovBox<?>, MapperModel> {
		
	public LovBoxMapper(FieldBeanWrapper mappingBeanWrapper, MapperModel model) {
		super(mappingBeanWrapper, model);
	}

	@Override
	public Object getPropertyValue() throws Exception {
		List<Object> result = new ArrayList<>();
		List<List<Object>> vals = getValue().getVal();
		if (vals != null) {
			for (List<Object> val : vals) {
				result.add(val.get(0));
			}
		}
		return result.toArray();
	}
	
	@Override
	public void setPropertyValue(Object object) throws Exception  {
		Object[] value;
		if (object != null) {
			if (object instanceof BeanWrapper) {
				List<Object> columns = new ArrayList<>();
				for (String column : getModel().getInfo()) {
					columns.add(((BeanWrapper) object).getPropertyValue(column));
				}
				value = columns.toArray();
			} else if (object.getClass().isArray()) {
				value = (Object[]) object;
			} else {	
				value = new Object[] { object };
			}
			getValue().setVal(value);
		}
	}

}
