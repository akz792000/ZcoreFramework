/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.handler;

import java.lang.annotation.Annotation;

import org.zcoreframework.mapping.core.BeanMapper.MappingDirection;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.model.ClassMapperModel;

public class ClassMapperHandler extends AbstractHandler<ClassMapperModel> {

	public ClassMapperHandler(Annotation annotation) throws InstantiationException, IllegalAccessException {
		super(annotation);		
	}
	
	@Override
	public void mapping(FieldBeanWrapper source, FieldBeanWrapper target, MappingDirection mappingDirection) throws Exception {
		super.mapping(
				(FieldBeanWrapper) getModel().getSource().getConstructors()[0].newInstance(source),
				(FieldBeanWrapper) getModel().getTarget().getConstructors()[0].newInstance(target),
				mappingDirection
		);
	}

}
