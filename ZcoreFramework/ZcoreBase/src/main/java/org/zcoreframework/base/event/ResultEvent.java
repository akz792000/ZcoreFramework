/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.event;

import org.zcoreframework.base.exception.BaseException;

public interface ResultEvent<M> {
	
	void onGetResult(M model) throws BaseException;

}