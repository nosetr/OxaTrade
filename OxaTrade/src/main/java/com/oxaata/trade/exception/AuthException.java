package com.oxaata.trade.exception;

public class AuthException extends ApiException{
  private static final long serialVersionUID = 7529064453426516902L;

	public AuthException(String message, String errorCode) {
      super(message, errorCode);
  }
}
