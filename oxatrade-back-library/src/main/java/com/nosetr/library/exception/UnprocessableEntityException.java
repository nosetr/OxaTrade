package com.nosetr.library.exception;

import org.springframework.lang.Nullable;

import com.nosetr.library.enums.ErrorEnum;

/**
 * {@code UnprocessableEntityException} is exception for form field validation
 * failures.
 * <p>The 422 (<em>Unprocessable Entity</em>) status code means the server
 * understands the content type of the request entity (<em>hence a 415
 * (Unsupported Media Type) status code is inappropriate</em>), and the syntax
 * of the request entity is correct (<em>thus a 400 (Bad Request) status code is
 * inappropriate</em>) but was unable to process the contained instructions.
 * <p>For example, this error condition may occur if an XML request body
 * contains well-formed (<em>i.e., syntactically correct</em>), but semantically
 * erroneous, XML instructions.
 * <p>Will be handled with
 * {@link ExceptionsHandler#handleUnprocessableEntityException(UnprocessableEntityException)
 * ExceptionsHandler} to become a 422
 * {@link org.springframework.http.HttpStatus#UNPROCESSABLE_ENTITY
 * HttpStatus.UNPROCESSABLE_ENTITY} with
 * message
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   https://github.com/zalando/problem-spring-web/tree/main
 * @see   https://www.baeldung.com/problem-spring-web
 * @see   ExceptionsHandler#handleUnprocessableEntityException(UnprocessableEntityException)
 */
public class UnprocessableEntityException extends ApiException {
	private static final long serialVersionUID = -486815773497437174L;

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
	public UnprocessableEntityException(ErrorEnum errorEnum, @Nullable Object... args) {
		super(String.format(errorEnum.getMessage(), args), errorEnum.getCode());
	}
}
