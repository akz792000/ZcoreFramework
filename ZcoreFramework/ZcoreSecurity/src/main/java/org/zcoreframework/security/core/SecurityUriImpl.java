/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.core;

import java.util.ArrayList;
import java.util.Collection;

import org.zcoreframework.base.annotation.JsonAutoDetectNone;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetectNone
public class SecurityUriImpl implements SecurityUri {

	@JsonProperty
	private Collection<String> privileges;

	@Override
	public Collection<String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<String> privileges) {
		this.privileges = privileges;
	}

	public void privilege(String uri) {
		if (privileges == null) {
			privileges = new ArrayList<String>();
		}
		privileges.add(uri);
	}

}
