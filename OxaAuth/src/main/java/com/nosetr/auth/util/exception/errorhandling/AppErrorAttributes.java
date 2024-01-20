package com.nosetr.auth.util.exception.errorhandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.google.common.base.CaseFormat;
import com.nosetr.auth.util.exception.ApiException;
import com.nosetr.auth.util.exception.AuthException;
import com.nosetr.auth.util.exception.EntityAlreadyExistsException;
import com.nosetr.auth.util.exception.EntityNotFoundException;
import com.nosetr.auth.util.exception.UnauthorizedException;
import com.nosetr.auth.util.exception.UnprocessableEntityException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

/**
 * Put HttpStatus for each exception.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Component
public class AppErrorAttributes extends DefaultErrorAttributes {

	// Default status
	private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

	/**
	 * Constructor
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.0
	 */
	public AppErrorAttributes() {
		super();
	}

	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

		Throwable error = getError(request);

		LinkedHashMap<String, Object> errorMap = new LinkedHashMap<>();
		ArrayList<Map<String, Object>> errorList = new ArrayList<>();

		// UNAUTHORIZED
		if (
			error instanceof AuthException || error instanceof UnauthorizedException
		) {
			status = HttpStatus.UNAUTHORIZED;
			errorMap.put("code", ((ApiException) error).getErrorCode());
			errorMap.put("message", error.getMessage());
			errorList.add(errorMap);
		} // UNAUTHORIZED JwtException
		else if (
			error instanceof ExpiredJwtException || error instanceof SignatureException
					|| error instanceof MalformedJwtException
		) {
			status = HttpStatus.UNAUTHORIZED;
			errorMap.put("code", "UNAUTHORIZED");
			errorMap.put("message", error.getMessage());
			errorList.add(errorMap);
		}
		// NOT_FOUND
		else if (error instanceof EntityNotFoundException) {
			status = HttpStatus.NOT_FOUND;
			errorMap.put("code", ((ApiException) error).getErrorCode());
			errorMap.put("message", error.getMessage());
			errorList.add(errorMap);
		}
		// CONFLICT
		else if (error instanceof EntityAlreadyExistsException) {
			status = HttpStatus.CONFLICT;
			errorMap.put("code", ((ApiException) error).getErrorCode());
			errorMap.put("message", error.getMessage());
			errorList.add(errorMap);
		}
		// UNPROCESSABLE_ENTITY
		else if (
			error instanceof MethodArgumentNotValidException
					|| error instanceof UnprocessableEntityException
		) {
			status = HttpStatus.UNPROCESSABLE_ENTITY;
			errorMap.put("code", "VALIDATION_FAILED");
			errorMap.put("message", error.getMessage());
			errorList.add(errorMap);
		}
		// UNPROCESSABLE_ENTITY "VALIDATION_FAILED" of fields
		else if (error instanceof WebExchangeBindException) {

			// Fields errors:
			Map<String, String> errors = new HashMap<>();
			BindingResult result = ((WebExchangeBindException) error).getBindingResult();
			result.getFieldErrors()
					.forEach(
							err -> {
								// Guava convert Camel Case in Snake Case:
								String field = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, err.getField());
								if (errors.containsKey(field)) {
									errors.put(
											field, String.format("%s,%s", errors.get(field), err.getDefaultMessage())
									);
								} else {
									errors.put(field, err.getDefaultMessage());
								}
							}
					);

			status = HttpStatus.UNPROCESSABLE_ENTITY;
			errorMap.put("code", "FIELD_VALIDATION_FAILED");
			errorMap.put("message", errors);
			errorList.add(errorMap);
		}
		// BAD_REQUEST
		else if (error instanceof ApiException) {
			status = HttpStatus.BAD_REQUEST;
			errorMap.put("code", ((ApiException) error).getErrorCode());
			errorMap.put("message", error.getMessage());
			errorList.add(errorMap);
		}
		// other
		else {

			String message = error.getMessage();
			if (message == null)
				message = error.getClass()
						.getName();

			status = (message.equals("404 NOT_FOUND")) ? HttpStatus.NOT_FOUND
					: HttpStatus.INTERNAL_SERVER_ERROR;
			errorMap.put("code", (message.equals("404 NOT_FOUND")) ? "NOT_FOUND"
					:"INTERNAL_ERROR");
			errorMap.put("message", message);
			errorList.add(errorMap);
		}

		HashMap<String, Object> errors = new HashMap<>();
		errors.put("errors", errorList);

		Map<String, Object> errorAttributes = super.getErrorAttributes(request, ErrorAttributeOptions.defaults());
		errorAttributes.put("status", status.value());
		errorAttributes.put("errors", errors);

		return errorAttributes;
	}
}
