package com.nosetr.auth.enums;

public enum OAuth2ProvidersEnum {
	GOOGLE("google"),
	FACEBOOK("facebook");
	
	private String name;

	private OAuth2ProvidersEnum(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
