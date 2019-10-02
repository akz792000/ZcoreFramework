/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.business.model;

import org.zcoreframework.base.core.Executor;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.model.Actionable;

public interface BusinessModel<M extends Actionable<?>> extends Executor {
	
	public void loadModel() throws BaseException;
	
	public M getModel() throws BaseException;
	
}
