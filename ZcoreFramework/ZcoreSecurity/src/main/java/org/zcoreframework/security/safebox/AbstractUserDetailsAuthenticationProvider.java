/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.security.safebox;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.util.Assert;

// instead of org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider

public abstract class AbstractUserDetailsAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {

	protected final Log logger = LogFactory.getLog(getClass());

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();
	private UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();
	private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

	public final void afterPropertiesSet() throws Exception {
		Assert.notNull(this.messages, "A message source must be set");
	}
	
	@SuppressWarnings("serial")
	public class UserDetailsException extends AuthenticationException {

		public UserDetailsException(String msg) {
			super(msg);
		}

	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.isInstanceOf(UserDetailsAuthenticationToken.class, authentication,
				messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports", "Only UsernamePasswordAuthenticationToken is supported"));

		UserDetails user = (UserDetails) authentication.getPrincipal();

		if (user == null) {
			throw new UserDetailsException("User details invalid.");
		}

		try {
			preAuthenticationChecks.check(user);
		} catch (AuthenticationException exception) {
			throw exception;
		}

		postAuthenticationChecks.check(user);

		Object principalToReturn = user;

		return createSuccessAuthentication(principalToReturn, authentication, user);
	}

	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
		UserDetailsAuthenticationToken result = new UserDetailsAuthenticationToken(principal, authoritiesMapper.mapAuthorities(user.getAuthorities()));
		result.setDetails(authentication.getDetails());

		return result;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}

	public boolean supports(Class<?> authentication) {
		return (UserDetailsAuthenticationToken.class.isAssignableFrom(authentication));
	}

	protected UserDetailsChecker getPreAuthenticationChecks() {
		return preAuthenticationChecks;
	}

	public void setPreAuthenticationChecks(UserDetailsChecker preAuthenticationChecks) {
		this.preAuthenticationChecks = preAuthenticationChecks;
	}

	protected UserDetailsChecker getPostAuthenticationChecks() {
		return postAuthenticationChecks;
	}

	public void setPostAuthenticationChecks(UserDetailsChecker postAuthenticationChecks) {
		this.postAuthenticationChecks = postAuthenticationChecks;
	}

	public void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
		this.authoritiesMapper = authoritiesMapper;
	}

	private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
		public void check(UserDetails user) {
			if (!user.isAccountNonLocked()) {
				logger.debug("User account is locked");

				throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
			}

			if (!user.isEnabled()) {
				logger.debug("User account is disabled");

				throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
			}

			if (!user.isAccountNonExpired()) {
				logger.debug("User account is expired");

				throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
			}
		}
	}

	private class DefaultPostAuthenticationChecks implements UserDetailsChecker {
		public void check(UserDetails user) {
			if (!user.isCredentialsNonExpired()) {
				logger.debug("User account credentials have expired");

				throw new CredentialsExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired",
						"User credentials have expired"));
			}
		}
	}
}
