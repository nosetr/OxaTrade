package com.nosetr.auth.enums;

import com.nosetr.auth.util.helper.MessageSourceHelper;

import lombok.Getter;

/**
 * Enum for errors.
 * <p>
 * <b>Example:</b>
 * <p>
 * {@code return Mono.error(
							new EntityAlreadyExistsException(ErrorEnum.USER_WITH_EMAIL_ALREADY_EXISTS, email)
					);}
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
public enum ErrorEnum {

	USER_WITH_EMAIL_ALREADY_EXISTS(
			"EMAIL_ALREADY_IN_USE", "exception.email.alreadyExists"
	),
	INVALID_EMAIL_IS_REQUESTED(
			"INVALID_EMAIL", "exception.email.invalid"
	),
	INVALID_PASSWORD_IS_REQUESTED(
			"INVALID_PASSWORD", "exception.password.invalid"
	),
	USER_NOT_FOUND(
			"USER_NOT_FOUND", "exception.user.notFound"
	),
	USER_ACCOUNT_IS_DISABLED(
			"USER_ACCOUNT_DISABLED", "exception.user.disabled"
	),
	TOKEN_IS_EXPIRED(
			"TOKEN_EXPIRED", "exception.access.tokenExpired"
	),
	NEWS_EMAIL_ALREADY_EXISTS(
			"EMAIL_ALREADY_IN_USE", "exception.newsemail.alreadyExists"
	);

	@Getter
	private final String code;

	private final String message;

	/**
	 * Constructor
	 * 
	 * @autor         Nikolay Osetrov
	 * @since         0.1.0
	 * @param code
	 * @param message Translated with {@link MessageSourceHelper}
	 */
	private ErrorEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Get with {@link MessageSourceHelper} translated message.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return String
	 * @see    MessageSourceHelper
	 */
	public String getMessage() { return MessageSourceHelper.getMessage(message); }
}
