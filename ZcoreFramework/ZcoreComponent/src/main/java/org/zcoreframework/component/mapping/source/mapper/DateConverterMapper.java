/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.mapping.source.mapper;

import java.util.Date;

import org.zcoreframework.component.dateconverter.DateConverter;
import org.zcoreframework.component.mapping.MapperModel;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.mapper.ModelFieldBeanMapperImpl;

public class DateConverterMapper extends ModelFieldBeanMapperImpl<DateConverter, MapperModel> {
		
	public DateConverterMapper(FieldBeanWrapper mappingBeanWrapper, MapperModel model) {
		super(mappingBeanWrapper, model);
	}

	@Override
	public Object getPropertyValue() throws Exception {
		return getValue().getTime();
	}
	
	@Override
	public void setPropertyValue(Object object) throws Exception {
		getValue().setTime((Date) object);
	}

}
