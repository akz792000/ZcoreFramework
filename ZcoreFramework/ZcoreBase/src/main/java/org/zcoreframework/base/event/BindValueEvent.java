/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.event;

import org.zcoreframework.base.exception.BaseException;

public interface BindValueEvent {
	
	public void onBindValue(Object object) throws BaseException;
		
}
