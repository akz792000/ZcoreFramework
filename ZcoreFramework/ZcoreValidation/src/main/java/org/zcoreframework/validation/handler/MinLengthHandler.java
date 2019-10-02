/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;

import org.zcoreframework.validation.model.ValueModel;

public class MinLengthHandler extends AbstractHandler<ValueModel<Integer>, String> {

	public MinLengthHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
	}

	@Override
	protected boolean doValidate(String object) {
		return object.length() >= getModel().getValue();
	}
	
}