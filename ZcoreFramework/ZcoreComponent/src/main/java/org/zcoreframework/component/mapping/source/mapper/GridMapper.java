/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.mapping.source.mapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.zcoreframework.component.grid.Grid;
import org.zcoreframework.component.grid.GridColumn;
import org.zcoreframework.component.grid.GridSimple;
import org.zcoreframework.component.mapping.MapperModel;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.mapper.ModelFieldBeanMapperImpl;

public class GridMapper extends ModelFieldBeanMapperImpl<Grid, MapperModel> {

	public GridMapper(FieldBeanWrapper mappingBeanWrapper, MapperModel model) {
		super(mappingBeanWrapper, model);
	}

	@Override
	public Object getPropertyValue() throws Exception {
		return getValue().getSelectedRowsByName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setPropertyValue(Object object) throws Exception {
		List<Object> value = new ArrayList<>();
		for (Iterator<Object> iterator = ((Iterable<Object>) object).iterator(); iterator.hasNext();) {
			Object element = iterator.next();
			List<Object> row = new ArrayList<>();
			BeanWrapper beanWrapper = new BeanWrapperImpl(element);	
			for (GridColumn gridColumn : getValue().getColumns()) {
				String name = gridColumn.getName();				
				if (name.equals("status")) {
					row.add(0);	
				} else {
					row.add(beanWrapper.getPropertyValue(name));
				}
			}
			value.add(row.toArray());
		}
		((GridSimple) getValue()).getDao().setData(value);
	}

}