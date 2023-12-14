package com.oxaata.trade.util.exception;

import lombok.Getter;

/**
 * API exception.
 * <p>Help to become an {@code errorCode} by
 * {@link com.oxaata.trade.util.exception.errorhandling.AppErrorAttributes
 * AppErrorAttributes}.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 5195063146082838366L;

	@Getter
	protected String errorCode;

	public ApiException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
