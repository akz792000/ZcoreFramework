/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;
import java.util.Collection;

import org.zcoreframework.validation.model.ValueModel;

@SuppressWarnings("rawtypes")
public class MinSizeHandler extends AbstractHandler<ValueModel<Integer>, Collection> {

	public MinSizeHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
	}

	@Override
	protected boolean doValidate(Collection object) {
		return object.size() >= getModel().getValue();
	}

}
