/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map;

import java.util.List;

public class JoinProperty {

	private String name;
	
	private Class<?> clazz;

	private List<JoinProperty> properties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Class<?> getClazz() {
		return clazz;
	}
	
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public List<JoinProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<JoinProperty> properties) {
		this.properties = properties;
	}
	
	public JoinProperty() {
		
	}
	
	public JoinProperty(String name) {
		this.name = name;
	}
	
	public JoinProperty(String name, Class<?> clazz, List<JoinProperty> properties) {
		this.name = name;
		this.clazz = clazz;
		this.properties = properties;
	}

}
