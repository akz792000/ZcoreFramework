/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.exception;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.zcoreframework.base.util.MessageSourceUtils;

@SuppressWarnings("serial")
public class BaseException extends Exception {
	
	private static final Log log = LogFactory.getLog(BaseException.class);
	
	private Object[] args;
	
	public Object[] getArgs() {
		return args;
	}
	
	public void setArgs(Object[] args) {
		this.args = args;
	}
		
	public BaseException() {
		super();
	}
	
	private static String getMsg(String message) {
		return MessageSourceUtils.getMessageSource().getMessage(message, null, message, LocaleContextHolder.getLocale());
	}

	public BaseException(String message) {		
		super(getMsg(message));	
		if (log.isErrorEnabled()) {
			log.error("Exception: " + message);
		}
	}	

    public BaseException(String message, Throwable cause) {
    	super(getMsg(message), cause);   
    	if (log.isErrorEnabled()) {
			log.error("Exception: " + message);
		}
    }
		
	public BaseException(String message, Object[] args) {		
		this(message);
		this.args = args;		
	}			

    public BaseException(String message, Object[] args, Throwable cause) {
    	this(message, cause);
    	this.args = args;
    }	
	
	public Object getMessageArgs() {
		if (args != null) {
			return Arrays.asList(getMessage(), Arrays.asList(args));
		}
		return getMessage();
	}
	
}