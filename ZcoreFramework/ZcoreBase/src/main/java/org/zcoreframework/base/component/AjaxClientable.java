/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.component;

import org.zcoreframework.base.exception.BaseException;


public interface AjaxClientable extends Clientable {
	
	Component getClientModel();
	
	ResponseResult partial() throws BaseException;
	
}