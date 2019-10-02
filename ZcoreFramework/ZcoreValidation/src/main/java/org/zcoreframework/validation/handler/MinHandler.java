/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;

import org.zcoreframework.validation.model.ValueModel;

public class MinHandler extends AbstractHandler<ValueModel<Number>, Number> {

	public MinHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
	}

	@Override
	protected boolean doValidate(Number object) {
		return object.doubleValue() >= getModel().getValue().doubleValue();
	}	
	
}
