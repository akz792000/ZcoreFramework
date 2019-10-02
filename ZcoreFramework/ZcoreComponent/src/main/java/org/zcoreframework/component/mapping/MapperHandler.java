/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.mapping;

import java.lang.annotation.Annotation;

import org.zcoreframework.mapping.core.BeanMapper.MappingDirection;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.handler.AbstractHandler;

public class MapperHandler extends AbstractHandler<MapperModel> {
	
	public MapperHandler(Annotation annotation) throws InstantiationException, IllegalAccessException {
		super(annotation);
	}
	
	@Override
	public void mapping(FieldBeanWrapper source, FieldBeanWrapper target, MappingDirection mappingDirection) throws Exception {
		// get source and target class
		Class<?> sourceClass = getModel().getSource();
		Class<?> targetClass = getModel().getTarget();
				
		// set source class
		if (sourceClass == null) {
			sourceClass = MapperUtils.getDefaultMapper(source.getPropertyType());	
			getModel().setSource(sourceClass);
		}
		
		// mapping
		super.mapping(
				(FieldBeanWrapper) sourceClass.getConstructors()[0].newInstance(source, getModel()), 
				(FieldBeanWrapper) targetClass.getConstructors()[0].newInstance(target, getModel()),	
				mappingDirection
		);
	}	
	
}
