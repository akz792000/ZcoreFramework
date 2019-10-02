/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.model;

import java.io.Serializable;

import org.zcoreframework.base.annotation.JsonAutoDetectNone;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetectNone
public class PropertyModelImpl implements PropertyModel, Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty
	private Long id;
	
	@JsonProperty
	private Long code;
	
	@JsonProperty
	private String name;
		
	@JsonProperty
	private Object object;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Object getObject() {
		return object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
	
	public PropertyModelImpl() {}
	
	public PropertyModelImpl(Long id, Long code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public PropertyModelImpl(Long id, Long code, String name, Object object) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.object = object;
	}	
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof PropertyModelImpl) {
			return id.equals(((PropertyModelImpl) obj).id);
		}

		return false;
	}
	
}
