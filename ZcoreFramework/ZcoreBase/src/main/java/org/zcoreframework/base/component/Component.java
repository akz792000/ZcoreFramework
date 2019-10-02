/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.component;

import java.lang.annotation.Annotation;

public interface Component extends Cloneable, Initable {
		
	void clean();
	
	Object parseValue(String text);
	
	void bindValue(Object object);
	
	Object clone() throws CloneNotSupportedException;
	
	Annotation getAnnotation();
	
	void setAnnotation(Annotation annotation);	
		
	void processAnnotation() throws Throwable;	
	
}
