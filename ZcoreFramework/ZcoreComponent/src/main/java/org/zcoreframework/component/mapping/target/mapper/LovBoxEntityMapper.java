package org.zcoreframework.component.mapping.target.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanWrapperImpl;
import org.zcoreframework.base.dao.DefaultEntityManager;
import org.zcoreframework.base.exception.ClientException;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.component.mapping.MapperModel;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.mapper.ModelFieldBeanMapperImpl;

public class LovBoxEntityMapper extends ModelFieldBeanMapperImpl<Object, MapperModel> {
	
	public LovBoxEntityMapper(FieldBeanWrapper mappingBeanWrapper, MapperModel model) throws Exception {
		super(mappingBeanWrapper, model);
	}

	@Override
	public Object getPropertyValue() throws Exception {
		Object result = getValue();
		return result == null ? result : new BeanWrapperImpl(result); 
	}

	@Override
	public void setPropertyValue(Object object) throws Exception {
		// get value
		Object value = getValue();
		
		// get entity manager
		DefaultEntityManager entityManager = (DefaultEntityManager) ApplicationContextUtils.createBean(DefaultEntityManager.class);	
		
		// set property value
		if (value instanceof List) {
			List<Object> result = new ArrayList<>();
			for (Object obj : (Object[]) object) {
				Long numeric = Long.parseLong(String.valueOf(obj));
				Object entity = entityManager.getEntityManager().find(getClazz(), numeric);
				if (entity == null) {
					ClientException.deliver("Entity does not exist", new Object[] { getClazz().getName() });
				}
				result.add(entity);
			}
			super.setPropertyValue(result);	
		} else {
			Object[] objects = (Object[]) object;
			Object entity = null;
			if (objects.length != 0) { 
				Long numeric = Long.parseLong(String.valueOf(objects[0]));
				entity = entityManager.getEntityManager().find(getClazz(), numeric);
				if (entity == null) {
					ClientException.deliver("Entity does not exist", new Object[] { getClazz().getName() });
				}				
			}
			super.setPropertyValue(entity);
		}
				
	}

}