/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.captcha.util;

/**
 * This interface determines if a class can be configured by properties handled
 * by config manager.
 */
public abstract class Configurable {
	
	private Config config;

	public Config getConfig() {
		return this.config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
}
