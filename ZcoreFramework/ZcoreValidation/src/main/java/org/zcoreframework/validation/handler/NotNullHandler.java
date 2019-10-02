/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;

import org.zcoreframework.validation.model.BaseModel;

public class NotNullHandler extends AbstractHandler<BaseModel, Object> {
		
	public NotNullHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, false);
	}

	@Override
	protected boolean doValidate(Object object) {
		return object != null;
	}

}