/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;
import java.util.Collection;

import org.zcoreframework.validation.model.BaseModel;

@SuppressWarnings("rawtypes")
public class NotEmptyHandler extends AbstractHandler<BaseModel, Collection> {

	public NotEmptyHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
	}

	@Override
	protected boolean doValidate(Collection object) {
		return object.size() != 0;
	}

}
