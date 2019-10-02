/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.core;

import java.util.List;

import org.springframework.security.core.session.SessionInformation;
import org.zcoreframework.security.model.UserInfo;

public class SessionRegistryImpl extends SessionRegistryClusterImpl {
	
	public List<SessionInformation> getAllSessionInformation(long id, boolean includeExpiredSessions) {
		return super.getAllSessions(new UserInfo(id), includeExpiredSessions);
	}
	
}
