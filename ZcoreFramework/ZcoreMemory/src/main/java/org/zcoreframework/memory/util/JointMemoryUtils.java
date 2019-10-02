/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.memory.util;

import org.zcoreframework.memory.JointMemory;

public class JointMemoryUtils {
	
	private static JointMemory jointMemory;
	
	public static JointMemory getJointMemory() {
		return jointMemory;
	}
	
	public void setJointMemory(JointMemory jointMemory) {
		JointMemoryUtils.jointMemory = jointMemory;
	}
	
}
