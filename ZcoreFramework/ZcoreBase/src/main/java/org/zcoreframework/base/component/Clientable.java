/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.component;

import org.zcoreframework.base.exception.BaseException;

public interface Clientable extends Valuable {
	
	Object data() throws BaseException;
	
}