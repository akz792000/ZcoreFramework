/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;

import org.zcoreframework.validation.model.RangeModel;

public class LengthHandler extends AbstractHandler<RangeModel<Integer>, String> {
	
	private final MinLengthHandler minLengthHandler = new MinLengthHandler(null);
	
	private final MaxLengthHandler maxLengthHandler = new MaxLengthHandler(null);
	
	public LengthHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
		minLengthHandler.getModel().setValue(getModel().getMin());
		maxLengthHandler.getModel().setValue(getModel().getMax());
	}

	@Override
	protected boolean doValidate(String object) {
		return minLengthHandler.doValidate(object) && maxLengthHandler.doValidate(object);
	}
	
}