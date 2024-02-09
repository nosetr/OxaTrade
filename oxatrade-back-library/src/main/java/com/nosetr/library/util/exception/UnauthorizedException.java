package com.nosetr.library.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nosetr.library.enums.ErrorEnum;

/**
 * Exception for unauthorized access.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ApiException {
	private static final long serialVersionUID = -6571994714217148411L;

	/**
	 * Controller with {@link ErrorEnum}
	 * <p>Keep attention on {@code NullPointerException} if args is null and message
	 * have any placeholder (%s, %d, etc.)
	 * 
	 * @autor           Nikolay Osetrov
	 * @since           0.1.0
	 * @param errorEnum
	 * @param Object... args
	 * @see             ErrorEnum
	 */
	public UnauthorizedException(ErrorEnum errorEnum, @Nullable Object... args) {
		super(String.format(errorEnum.getMessage(), args), errorEnum.getCode());
	}
}
