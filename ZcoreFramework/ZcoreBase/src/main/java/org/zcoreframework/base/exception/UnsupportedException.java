/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class UnsupportedException extends Exception {
	
	private static final Log log = LogFactory.getLog(UnsupportedException.class);
	
	public UnsupportedException() {
		super();
	}

	public UnsupportedException(String message) {		
		super(message);	
		if (log.isErrorEnabled()) {
			log.error("Exception: " + message);
		}
	}	

}