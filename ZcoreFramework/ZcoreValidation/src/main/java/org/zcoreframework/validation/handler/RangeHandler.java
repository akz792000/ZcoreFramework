/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;

import org.zcoreframework.validation.model.RangeModel;

public class RangeHandler extends AbstractHandler<RangeModel<Number>, Number> {

	private final MinHandler minHandler = new MinHandler(null);
	
	private final MaxHandler maxHandler = new MaxHandler(null);
	
	public RangeHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
		minHandler.getModel().setValue(getModel().getMin());
		maxHandler.getModel().setValue(getModel().getMax());
	}

	@Override
	protected boolean doValidate(Number object) {
		return minHandler.doValidate(object) && maxHandler.doValidate(object);
	}
	
}
