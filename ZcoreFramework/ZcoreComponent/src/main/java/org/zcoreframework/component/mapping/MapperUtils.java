/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.mapping;

import java.util.HashMap;
import java.util.Map;

import org.zcoreframework.component.dateconverter.DateConverter;
import org.zcoreframework.component.grid.Grid;
import org.zcoreframework.component.grid.GridMethodInvoker;
import org.zcoreframework.component.grid.GridQuery;
import org.zcoreframework.component.grid.GridSimple;
import org.zcoreframework.component.listvalue.ListValue;
import org.zcoreframework.component.listvalue.ListValueQuery;
import org.zcoreframework.component.listvalue.ListValueStatic;
import org.zcoreframework.component.lovbox.LovBox;
import org.zcoreframework.component.lovbox.LovBoxGridMethodInvoker;
import org.zcoreframework.component.lovbox.LovBoxGridQuery;
import org.zcoreframework.component.lovbox.LovBoxGridSimple;
import org.zcoreframework.component.lovbox.LovBoxTreeQuery;
import org.zcoreframework.component.mapping.source.mapper.DateConverterMapper;
import org.zcoreframework.component.mapping.source.mapper.GridMapper;
import org.zcoreframework.component.mapping.source.mapper.ListValueMapper;
import org.zcoreframework.component.mapping.source.mapper.LovBoxMapper;
import org.zcoreframework.mapping.mapper.ModelFieldBeanMapperImpl;

public class MapperUtils {
	
	private static Map<Class<?>, Class<?>> defaultMapper;
	
	static {
		defaultMapper = new HashMap<Class<?>, Class<?>>();
		
		// DateConveter
		defaultMapper.put(DateConverter.class, DateConverterMapper.class);
		
		// ListValue
		defaultMapper.put(ListValue.class, ListValueMapper.class);
		defaultMapper.put(ListValueQuery.class, ListValueMapper.class);
		defaultMapper.put(ListValueStatic.class, ListValueMapper.class);
		
		// Grid
		defaultMapper.put(Grid.class, GridMapper.class);
		defaultMapper.put(GridMethodInvoker.class, GridMapper.class);
		defaultMapper.put(GridQuery.class, GridMapper.class);
		defaultMapper.put(GridSimple.class, GridMapper.class);
		
		// LovBox
		defaultMapper.put(LovBox.class, LovBoxMapper.class);
		defaultMapper.put(LovBoxGridMethodInvoker.class, LovBoxMapper.class);
		defaultMapper.put(LovBoxGridQuery.class, LovBoxMapper.class);
		defaultMapper.put(LovBoxGridSimple.class, LovBoxMapper.class);
		defaultMapper.put(LovBoxTreeQuery.class, LovBoxMapper.class);		
	};
	
	public static Class<?> getDefaultMapper(Class<?> clazz) {
		Class<?> result = defaultMapper.get(clazz);
		if (result == null) {
			result = ModelFieldBeanMapperImpl.class;
		}
		return result;
	}

}
