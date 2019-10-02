/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.zcoreframework.base.annotation.JsonAutoDetectNone;
import org.zcoreframework.base.model.PropertyModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * instead of SimpleGrantedAuthority we use ComplexGrantedAuthority
 */

@JsonAutoDetectNone
public class ComplexGrantedAuthority implements GrantedAuthority {
	
	public static final String SEPARAT_CHARACTER = ":";

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	@JsonProperty
	private PropertyModel organization;
	
	@JsonProperty
	private PropertyModel position;
	
	@JsonProperty
	private Date date;
	
	@JsonProperty
	private List<PropertyModel> roles = new ArrayList<>();
	
	public PropertyModel getOrganization() {
		return organization;
	}
	
	public void setOrganization(PropertyModel organization) {
		this.organization = organization;
	}

	public PropertyModel getPosition() {
		return position;
	}
	
	public void setPosition(PropertyModel position) {
		this.position = position;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public List<PropertyModel> getRoles() {
		return roles;
	}
	
	public void setRoles(List<PropertyModel> roles) {
		this.roles = roles;
	}
	
	@Override
	@JsonIgnore
	public String getAuthority() {
		String result = "";
		for (PropertyModel role : roles) {
			result += result == "" ? role.getName().trim() : "," + role.getName().trim();
		}
		return result;
	}
	
	@JsonIgnore
	public ComplexGrantedAuthority getComplexAuthority() {
		return this;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof ComplexGrantedAuthority) {
			return 
					organization.equals(((ComplexGrantedAuthority) obj).organization) &
					position.equals(((ComplexGrantedAuthority) obj).position) &
					getAuthority().equals(((ComplexGrantedAuthority) obj).getAuthority());
		}

		return false;
	}

	public int hashCode() {
		String unique = String.valueOf(this.organization.getId()) + String.valueOf(this.position.getId()) + String.valueOf(getAuthority());
		return unique.hashCode();
	}

	public String toString() {
		return getAuthority();
	}
}
