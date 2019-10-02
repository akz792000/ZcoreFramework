/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.service.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.zcoreframework.base.annotation.ServiceAction;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.web.business.model.BusinessModel;

public abstract class AbstractBusinessServiceImpl<B extends BusinessModel<?>> extends AbstractServiceImpl {

	private B business;
	
	@SuppressWarnings("unchecked")
	public AbstractBusinessServiceImpl() {
		// get types
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		Type[] types = genericSuperclass.getActualTypeArguments();
		
		// business
		business = ApplicationContextUtils.getBeanFactory().createBean((Class<B>) types[0]);
				
	}
	
	public B getBusiness() {
		return business;
	}
	
	@Override
	public Map<String, Object> param() throws BaseException {
		if (getBusiness().getModel().getIdField().getValue() != null) {
			business.loadModel();
		}
		return super.param();
	}
	
	public void afterExecute(Object object) throws Exception {
		// nop
	}
		
	@ServiceAction
	public Object execute() throws Exception {
		Object result = business.execute();
		afterExecute(result);
		return result;
	}	
	
}
