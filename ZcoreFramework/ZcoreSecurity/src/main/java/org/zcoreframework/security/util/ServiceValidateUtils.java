package org.zcoreframework.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.zcoreframework.base.model.PropertyModel;
import org.zcoreframework.security.core.ComplexGrantedAuthority;
import org.zcoreframework.security.core.SecurityUri;
import org.zcoreframework.security.core.ServiceValidateExecutor;
import org.zcoreframework.security.model.UserInfo;

import java.util.Collection;

public class ServiceValidateUtils {

	public static boolean check(String uri, String privilege) {
		if (privilege.equals("/"))
			return true;
		if (uri.startsWith(privilege)) {
			String extera = uri.substring(privilege.length());
			if (extera.length() == 0 || extera.charAt(0) == '/' || extera.charAt(0) == '_') {
				return true;
			}
		}
		return false;
	}

	public static boolean validate(UserInfo userInfo, String uri) throws Exception {
		return validate(userInfo, new ServiceValidateExecutor() {

			@Override
			public boolean execute(String privilege) throws Exception {
				return check(uri, privilege);
			}

		});
	}

	public static boolean validate(UserInfo userInfo, ServiceValidateExecutor executor) throws Exception {
		for (GrantedAuthority grantedAuthority : userInfo.getAuthorities()) {
			ComplexGrantedAuthority complexGrantedAuthority = (ComplexGrantedAuthority) grantedAuthority;
			for (PropertyModel propertyModel : complexGrantedAuthority.getRoles()) {

				// get security uri
				SecurityUri securityUri = (SecurityUri) propertyModel.getObject();
				if (securityUri != null) {

					// privilege
					Collection<String> privileges = securityUri.getPrivileges();
					if (privileges != null) {
						for (String privilege : privileges) {
							if (executor.execute(privilege)) {
								return true;
							}
						}
					}

				}
			}
		}
		return false;
	}

}