package com.oxaata.trade.enums;

import com.oxaata.trade.util.helper.MessageSourceHelper;

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

	USER_NOT_FOUND(
			"USER_NOT_FOUND", "exception.notFound.user"
	),
	USER_WITH_EMAIL_ALREADY_EXISTS(
			"EMAIL_ALREADY_IN_USE", "exception.alreadyExists.email"
	);

	@Getter
	private final String code;

	private final String message;

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
	 */
	public String getMessage() { return MessageSourceHelper.getMessage(message); }
}
