/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.core;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.security.model.UserInfo;

public class SecurityHelper {
	
	public static void setAuthentication(Authentication authentication) {
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static boolean isAnonymous() {	
		Authentication authentication = getAuthentication();
		if (authentication == null)
			return false;
		return authentication.getPrincipal().equals("anonymousUser") ? true : false;
	}
	
	public static boolean isAuthenticated() {
		Authentication authentication = getAuthentication();
		if (authentication == null) 
			return false;
		return authentication.isAuthenticated();
	}
	
	public static boolean isUserAuthenticated() {
		return (isAuthenticated() && !isAnonymous());
	}
	
	public static UserInfo getUserInfo() {
		Authentication authentication = getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			return principal instanceof UserInfo ? (UserInfo) principal : null;
		}
		return null;
	}
	
	public static Long getUserModelId() {
		UserInfo userInfo = getUserInfo();
		return (userInfo == null) ? -1 : userInfo.getId();	
	}	
	
	public static List<GrantedAuthority> getAuthorities() {
		UserInfo userInfo = getUserInfo(); 
		return (userInfo == null) ? null : userInfo.getAuthorities(); 
	}
	
	public static boolean isUserInRole(String role) {		
		if (getAuthorities() == null)
			return false;		
		for (GrantedAuthority grantedAuthority : getUserInfo().getAuthorities())
			if (grantedAuthority.getAuthority().equals(role))
				return true;		
		return false;
	}
	
	public static boolean hasRole(String role) {		
		Collection<? extends GrantedAuthority> userAuthorities = getAuthorities(); 		
		if (userAuthorities == null)
			return false;		
		RoleHierarchy roleHierarchy = (RoleHierarchy) ApplicationContextUtils.getApplicationContext().getBean("zcoreRoleHierarchy");
		if (roleHierarchy != null)
			userAuthorities = roleHierarchy.getReachableGrantedAuthorities(userAuthorities);
		for (GrantedAuthority grantedAuthority : userAuthorities)
			if (grantedAuthority.getAuthority().equals(role))
				return true;		
		return false;
	}
	
	public static void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
        if (session != null) 
            session.invalidate();
        SecurityContextHolder.clearContext();
	}

}
