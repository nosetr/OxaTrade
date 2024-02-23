package com.nosetr.library.exception;

import org.springframework.lang.Nullable;

import com.nosetr.library.enums.ErrorEnum;

/**
 * Authentications exception.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
public class AuthException extends ApiException {
	private static final long serialVersionUID = 7529064453426516902L;

	/**
	 * Controller with {@link ErrorEnum}
	 * <p>Keep attention on {@code NullPointerException} if args is null and message
	 * have any placeholder (%s, %d, etc.)
	 * 
	 * @autor           Nikolay Osetrov
	 * @since           0.1.0
	 * @param errorEnum
	 * @param Object... args
	 * @see ErrorEnum
	 */
	public AuthException(ErrorEnum errorEnum, @Nullable Object... args) {
		super(String.format(errorEnum.getMessage(), args), errorEnum.getCode());
	}
}
