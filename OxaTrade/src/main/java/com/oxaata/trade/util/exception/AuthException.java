package com.oxaata.trade.util.exception;

/**
 * Authentications exception.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
public class AuthException extends ApiException {
	private static final long serialVersionUID = 7529064453426516902L;

	public AuthException(String message, String errorCode) {
		super(message, errorCode);
	}
}
