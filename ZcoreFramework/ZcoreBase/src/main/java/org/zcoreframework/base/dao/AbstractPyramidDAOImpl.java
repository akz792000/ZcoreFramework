/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.dao;

import java.lang.reflect.ParameterizedType;

import org.springframework.util.ReflectionUtils;
import org.zcoreframework.base.dao.DefaultEntityManager;
import org.zcoreframework.base.model.PyramidModel;

public abstract class AbstractPyramidDAOImpl<M> extends DefaultEntityManager implements PyramidModel<M> {	
	
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
	public AbstractPyramidDAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		try {
			model = ((Class<M>) genericSuperclass.getActualTypeArguments()[0]).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			ReflectionUtils.handleReflectionException(e);
		}
	}
	
}