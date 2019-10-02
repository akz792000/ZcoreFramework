/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.base.type.ColumnType;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation = GridColumnParam.class, clazz = GridColumn.class)
public @interface GridColumnParam {

	String name();

	ColumnType type();

	String mask() default "";
	
	String filterBean() default "";
	
	String staticValue() default "";

}
