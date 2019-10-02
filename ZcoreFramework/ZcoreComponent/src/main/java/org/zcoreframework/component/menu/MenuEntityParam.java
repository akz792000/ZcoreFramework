/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.menu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.component.menu.MenuEntity;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=MenuEntityParam.class, clazz=MenuEntity.class)
public @interface MenuEntityParam {

	String entity();

}