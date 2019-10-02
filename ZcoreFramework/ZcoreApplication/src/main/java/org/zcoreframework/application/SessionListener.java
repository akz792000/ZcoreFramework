package org.zcoreframework.application;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		session.setMaxInactiveInterval(-1);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// session destroy
	}
	
}
