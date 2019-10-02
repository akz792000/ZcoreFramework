/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.log;

import java.util.HashMap;
import java.util.Map;

import org.zcoreframework.base.util.ApplicationContextUtils;

public class FactoryLog {

	public static Map<Class<? extends Logger>, Logger> repositories = new HashMap<>();

	public static Logger getLogger(Class<? extends Logger> clazz) {
		Logger logger = repositories.get(clazz);
		if (logger == null) {
			logger = ApplicationContextUtils.createBean(clazz);
			repositories.put(clazz, logger);	
		}
		return logger;
	}

}
