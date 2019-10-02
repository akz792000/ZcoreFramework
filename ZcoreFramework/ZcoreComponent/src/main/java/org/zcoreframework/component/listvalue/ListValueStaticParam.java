/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.component.entryset.EntrySetSimpleParam;
import org.zcoreframework.component.listvalue.ListValueStatic;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=ListValueStaticParam.class, clazz=ListValueStatic.class)
public @interface ListValueStaticParam {
	
	EntrySetSimpleParam[] items() default {};
	
}
