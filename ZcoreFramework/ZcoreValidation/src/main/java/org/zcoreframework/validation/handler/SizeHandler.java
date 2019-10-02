/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;
import java.util.Collection;

import org.zcoreframework.validation.model.RangeModel;

@SuppressWarnings("rawtypes")
public class SizeHandler extends AbstractHandler<RangeModel<Integer>, Collection> {

	private final MinSizeHandler minSizeHandler = new MinSizeHandler(null);
	
	private final MaxSizeHandler maxSizeHandler = new MaxSizeHandler(null);
	
	public SizeHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
		minSizeHandler.getModel().setValue(getModel().getMin());
		maxSizeHandler.getModel().setValue(getModel().getMax());
	}

	@Override
	public boolean doValidate(Collection object) {
		return minSizeHandler.doValidate(object) && maxSizeHandler.doValidate(object);
	}

}
