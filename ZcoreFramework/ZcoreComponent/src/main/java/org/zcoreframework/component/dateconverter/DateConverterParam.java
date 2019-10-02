/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.dateconverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.component.dateconverter.DateConverter;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=DateConverterParam.class, clazz=DateConverter.class)
public @interface DateConverterParam {
	
	public enum DateType {GREGORIAN, PERSIAN};
	
	public DateType type() default DateType.PERSIAN;
	
	public String format() default "yyyy/MM/dd";	

}
