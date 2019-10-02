/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.component;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;

import org.zcoreframework.base.model.PyramidModel;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.base.dao.PyramidDAO;

public abstract class AbstractPyramidComponentImpl<D extends AbstractPyramidDAOImpl<M>, M> extends AbstractComponentImpl implements PyramidDAO<D>, PyramidModel<M> {
		
	private D dao;
	
	@Override
	public D getDao() {
		return dao;
	}

	@Override
	public void setDao(D dao) {
		this.dao = dao;
	}
	
	@Override
	public M getModel() {
		return dao.getModel();
	}
	
	@Override
	public void setModel(M model) {
		dao.setModel(model);
	}
	
	@SuppressWarnings("unchecked")
	public AbstractPyramidComponentImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();		
		dao = (D) ApplicationContextUtils.createBean((Class<D>) genericSuperclass.getActualTypeArguments()[0]);	
	}
	
	@Override
	public Annotation getAnnotation() {
		return ((Component) getModel()).getAnnotation();
	}	
	
	@Override
	public void setAnnotation(Annotation annotation) {
		((Component) getModel()).setAnnotation(annotation);
	}
	
	@Override
	public void processAnnotation()	throws Throwable {
		((Component) getModel()).processAnnotation();	
	}
		
}
