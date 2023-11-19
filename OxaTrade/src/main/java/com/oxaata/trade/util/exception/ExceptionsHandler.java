package com.oxaata.trade.util.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oxaata.trade.dto.ApiResponseDto;

/**
 * The own exceptionHandler for all controllers to become ResponseEntity with
 * HttpStatus
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@ControllerAdvice
public class ExceptionsHandler {
	//implements ResponseEntityExceptionHandler {

	// TODO Logger for ExceptionsHandler
	//	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionsHandler.class);

	/**
	 * Return a custom HTTP status 422 instead of a default 400 on a spring
	 * validation.
	 * <p>The validation process would throw an
	 * {@link org.springframework.web.bind.MethodArgumentNotValidException},
	 * therefore we can add an exception handler to our controller
	 * 
	 * @autor            Nikolay Osetrov
	 * @since            0.1.0
	 * @param  exception
	 * @return           422
	 *                   {@link org.springframework.http.HttpStatus#UNPROCESSABLE_ENTITY
	 *                   HttpStatus.UNPROCESSABLE_ENTITY} without message
	 * @see              MethodArgumentNotValidException
	 * @see              https://github.com/anicetkeric/custom-password-validation/blob/master/src/main/java/com/passay/sample/custompasswordvalidation/web/BaseExceptionHandler.java
	 */
	//	@ExceptionHandler
	//	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
	//		return ResponseEntity.unprocessableEntity().body(exception.getMessage());
	//	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponseDto handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult()
				.getFieldErrors()
				.forEach(error -> {
					if (errors.containsKey(error.getField())) {
						errors.put(
								error.getField(), String.format("%s, %s", errors.get(error.getField()), error.getDefaultMessage())
						);
					} else {
						errors.put(error.getField(), error.getDefaultMessage());
					}
				}
				);
		return new ApiResponseDto(errors, "VALIDATION_FAILED");
	}

	/**
	 * Handler of {@code UnprocessableEntityException}, if form field validation
	 * failures.
	 * 
	 * @autor            Nikolay Osetrov
	 * @since            0.1.0
	 * @param  exception {@link UnprocessableEntityException}
	 * @return           422
	 *                   {@link org.springframework.http.HttpStatus#UNPROCESSABLE_ENTITY
	 *                   HttpStatus.UNPROCESSABLE_ENTITY} with message
	 * @see              UnprocessableEntityException
	 */
	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<String> handleUnprocessableEntityException(UnprocessableEntityException exception) {
		//		LOGGER.error(exception.getMessage());
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * Handler of {@code EntityNotFoundException}, if entity was not found.
	 * <p>It will be send the Http-Status of 404 (Not found) and not a 500-error.
	 * 
	 * @autor            Nikolay Osetrov
	 * @since            0.1.0
	 * @param  exception {@link EntityNotFoundException}
	 * @return           404 {@link org.springframework.http.HttpStatus#NOT_FOUND
	 *                   HttpStatus.NOT_FOUND} with message
	 * @see              EntityNotFoundException
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException exception) {
		//		LOGGER.error(exception.getMessage());
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Handler of {@code EntityAlreadyExistsException}, if entity with given
	 * parameters already exists.
	 * 
	 * @autor            Nikolay Osetrov
	 * @since            0.1.0
	 * @param  exception {@link EntityAlreadyExistsException}
	 * @return           409 {@link org.springframework.http.HttpStatus#CONFLICT
	 *                   HttpStatus.CONFLICT} with message
	 * @see              EntityAlreadyExistsException
	 */
	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<String> handleEntityAlreadyExistsException(EntityAlreadyExistsException exception) {
		//		LOGGER.error(exception.getMessage());
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
	}
}
