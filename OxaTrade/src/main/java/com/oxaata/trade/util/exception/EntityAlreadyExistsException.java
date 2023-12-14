package com.oxaata.trade.util.exception;

import org.springframework.lang.Nullable;

import com.oxaata.trade.enums.ErrorEnum;

/**
 * {@code EntityAlreadyExistsException} is exception, to check if the same
 * entity already exists.
 * <p>Will be handled with
 * {@link ExceptionsHandler#handleEntityAlreadyExistsException(EntityAlreadyExistsException)
 * ExceptionsHandler} to become a 409
 * {@link org.springframework.http.HttpStatus#CONFLICT HttpStatus.CONFLICT} with
 * message
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   java.lang.RuntimeException
 * @see   ExceptionsHandler#handleEntityAlreadyExistsException(EntityAlreadyExistsException)
 */
public class EntityAlreadyExistsException extends ApiException {

	private static final long serialVersionUID = -593945508436610247L;

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
	public EntityAlreadyExistsException(ErrorEnum errorEnum, @Nullable Object... args) {
		super(String.format(errorEnum.getMessage(), args), errorEnum.getCode());
	}
}
