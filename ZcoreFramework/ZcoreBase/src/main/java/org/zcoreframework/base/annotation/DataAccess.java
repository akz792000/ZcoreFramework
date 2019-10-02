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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repository
@Scope("prototype")
public @interface DataAccess {

	String value() default "";

}

