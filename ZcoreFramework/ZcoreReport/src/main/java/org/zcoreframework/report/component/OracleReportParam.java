/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.report.component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=OracleReportParam.class, clazz=OracleReport.class)
public @interface OracleReportParam {
	
	public String path() default "";
	
	public String name() default "";
	
	public String fileType() default "";
	
	public String template() default "";

}
