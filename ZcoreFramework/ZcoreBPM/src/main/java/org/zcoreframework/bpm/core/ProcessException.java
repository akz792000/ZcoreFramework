package org.zcoreframework.bpm.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
class ProcessException extends Exception {
	
	private static final Log log = LogFactory.getLog(ProcessException.class);
	
	public ProcessException() {
		super();
	}

	public ProcessException(String message) {		
		super(message);	
		if (log.isErrorEnabled()) {
			log.error("Exception: " + message);
		}
	}	

}
