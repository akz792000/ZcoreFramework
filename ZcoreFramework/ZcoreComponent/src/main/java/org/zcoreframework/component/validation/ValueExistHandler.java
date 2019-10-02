/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.validation;

import java.lang.annotation.Annotation;

import org.zcoreframework.base.component.Clientable;
import org.zcoreframework.validation.handler.AbstractHandler;
import org.zcoreframework.validation.model.BaseModel;

public class ValueExistHandler extends AbstractHandler<BaseModel, Clientable> {

	public ValueExistHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
	}

	@Override
	protected boolean doValidate(Clientable object) {
		return object.value() != null;
	}

}
