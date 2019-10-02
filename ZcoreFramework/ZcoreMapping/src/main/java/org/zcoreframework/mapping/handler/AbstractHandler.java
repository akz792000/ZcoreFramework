/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.zcoreframework.mapping.core.BeanMapper.MappingDirection;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.model.Model;

public abstract class AbstractHandler<M extends Model> implements Handler<M> {
	
	private final M model;
	
	@Override
	public M getModel() {
		return model;
	}
	
	@SuppressWarnings("unchecked")
	public AbstractHandler(Annotation annotation) throws InstantiationException, IllegalAccessException {
		// model
		Class<?> superClass = getClass();
		while (superClass.getSuperclass().getTypeParameters().length == 0) {
			superClass = superClass.getSuperclass();
		}
		ParameterizedType parameterizedType = (ParameterizedType) superClass.getGenericSuperclass();		
		Type type = parameterizedType.getActualTypeArguments()[0];
		if (type instanceof ParameterizedType) {
			type = ((ParameterizedType) type).getRawType();
		}
		model = ((Class<M>) type).newInstance();
		model.setProperties(annotation);
	}	
	
	@Override
	public void mapping(FieldBeanWrapper source, FieldBeanWrapper target, MappingDirection mappingDirection) throws Exception {		
		switch (mappingDirection) {
		case DIRECTION:
			if (!getModel().getMappingDirection().equals(MappingDirection.INDIRECTION)) {
				target.setPropertyValue(source.getPropertyValue());
			}
			break;
		case INDIRECTION:
			if (!getModel().getMappingDirection().equals(MappingDirection.DIRECTION)) {
				source.setPropertyValue(target.getPropertyValue());
			}
			break;
		default:
			break;
		}					
	}
	
}
