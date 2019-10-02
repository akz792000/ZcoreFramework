package org.zcoreframework.security.core;

public enum SystemChannelsType {

	INTERNET_BANK(3436L), MOBILE_BANK(3438L), BRANCH(3437L);

	private final Long id;

	SystemChannelsType(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return id;
	}

}