/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;

import org.zcoreframework.validation.model.BaseModel;

public class NotBlankHandler extends AbstractHandler<BaseModel, String> {

	public NotBlankHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
	}

	@Override
	protected boolean doValidate(String object) {
		return !object.isEmpty();
	}

}
