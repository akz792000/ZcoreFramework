/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.safebox;

public class UserDetailsAuthenticationProviderImpl extends AbstractUserDetailsAuthenticationProvider {

	public UserDetailsAuthenticationProviderImpl() {
		setPreAuthenticationChecks((user) -> {});
		setPostAuthenticationChecks((user) -> {});
	}

}
