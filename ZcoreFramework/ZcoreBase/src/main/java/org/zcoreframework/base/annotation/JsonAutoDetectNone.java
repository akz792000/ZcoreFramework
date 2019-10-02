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

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonAutoDetect(
		fieldVisibility = Visibility.NONE, 
		getterVisibility = Visibility.NONE, 
		setterVisibility = Visibility.NONE, 
		isGetterVisibility = Visibility.NONE, 
		creatorVisibility = Visibility.NONE
)
public @interface JsonAutoDetectNone {
	
}
