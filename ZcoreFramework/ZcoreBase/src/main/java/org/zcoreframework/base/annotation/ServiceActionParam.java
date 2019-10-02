/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceActionParam {
	
	/*
	 * in Java 8 you can get the name of method's parameter therefore until 
	 * update to Java 8, the value() has not any default value because
	 * we don't get the name of method's parameter
	 */
	//TODO must be updated in Java 8
	
	String value();
	
}

