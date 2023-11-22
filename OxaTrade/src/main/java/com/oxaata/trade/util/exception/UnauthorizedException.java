package com.oxaata.trade.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oxaata.trade.enums.ErrorEnum;

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
	 * Controller.
	 * 
	 * @autor         Nikolay Osetrov
	 * @since         0.1.0
	 * @param message
	 */
	public UnauthorizedException(String message) {
		super(message, "UNAUTHORIZED");
	}

	/**
	 * TODO set all exceptions in code to ErrorEnum
	 * Controller with {@link ErrorEnum}
	 * 
	 * @autor           Nikolay Osetrov
	 * @since           0.1.0
	 * @param errorEnum
	 * @param Object... args
	 * @see             ErrorEnum
	 */
	public UnauthorizedException(ErrorEnum errorEnum, Object... args) {
		super(String.format(errorEnum.getMessage(), args), errorEnum.getCode());
	}
}
