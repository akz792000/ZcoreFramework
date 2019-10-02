/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.model;

public class ClassMapperModel extends BaseModel {
	
	private Class<?> source;
	
	private Class<?> target;
	
	public Class<?> getSource() {
		return source;
	}
	
	public void setSource(Class<?> source) {
		this.source = source;
	}
	
	public Class<?> getTarget() {
		return target;
	}
	
	public void setTarget(Class<?> target) {
		this.target = target;
	}

}