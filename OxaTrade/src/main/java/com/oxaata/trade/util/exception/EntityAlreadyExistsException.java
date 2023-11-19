package com.oxaata.trade.util.exception;

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
public class EntityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -593945508436610247L;

	/**
	 * Controller
	 * 
	 * @autor         Nikolay Osetrov
	 * @since         0.1.0
	 * @param message as string on super
	 */
	public EntityAlreadyExistsException(String message) {
		super(message);
	}
}
