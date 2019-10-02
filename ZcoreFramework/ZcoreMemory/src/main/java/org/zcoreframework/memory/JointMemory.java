/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.memory;

public interface JointMemory {
	
	Object read(String key);
	
	void write(String key, Object value);
	
	void update(String key, Object value);

}