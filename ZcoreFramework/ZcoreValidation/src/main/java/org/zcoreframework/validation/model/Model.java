/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.model;

import java.lang.annotation.Annotation;

public interface Model {
	
	void setProperties(Annotation annotation);
	
	String getErrorCode();
	
	String getApplyIf();
	
	boolean isRejectable();
	
	Object[] getArgs();
		
}
