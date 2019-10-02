package org.zcoreframework.security.core;

public enum DeterrentState {

	SECURITY_USER_MULTI_ORGANIZATIONAL(1), SECURITY_USER_ENABLED(2), SECURITY_USER_CREDENTIAL_EXPIRED(3), SECURITY_USER_CREDENTIAL_CHANGE(4);

	private int value;

	DeterrentState(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}

}
