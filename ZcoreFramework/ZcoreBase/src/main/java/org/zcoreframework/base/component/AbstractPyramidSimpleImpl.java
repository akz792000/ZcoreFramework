/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.component;

import java.lang.reflect.ParameterizedType;

import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.model.PyramidModel;

public class AbstractPyramidSimpleImpl<M> extends AbstractComponentImpl implements PyramidModel<M> {
	
	private M model;
	
	@Override
	public M getModel() {
		return model;
	}

	@Override
	public void setModel(M model) {
		this.model = model;
	}
	
	@SuppressWarnings("unchecked")
	public AbstractPyramidSimpleImpl() throws InstantiationException, IllegalAccessException {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		model = ((Class<M>) genericSuperclass.getActualTypeArguments()[0]).newInstance();
	}

}
