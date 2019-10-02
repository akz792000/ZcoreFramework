/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.entryset;

import java.lang.reflect.InvocationTargetException;

import org.springframework.util.ReflectionUtils;

public interface EntrySet {

	default String getEntryMethod(String name) {
		try {
			return (String) ReflectionUtils.findMethod(getClass(), name).invoke(this, new Object[] {});
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	};

	String getEntryLabel();

	Object getEntryValue();

}
