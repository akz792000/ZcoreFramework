/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zcoreframework.security.model.UserInfo;

public interface AuthenticationDAO<M extends UserInfo> {
	
	M loadUser(String username, String password) throws UsernameNotFoundException;
	
	void check(M userInfo);
		
}
