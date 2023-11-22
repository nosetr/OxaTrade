package com.oxaata.trade.util.exception.errorhandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.oxaata.trade.util.exception.ApiException;
import com.oxaata.trade.util.exception.AuthException;
import com.oxaata.trade.util.exception.EntityAlreadyExistsException;
import com.oxaata.trade.util.exception.EntityNotFoundException;
import com.oxaata.trade.util.exception.UnauthorizedException;
import com.oxaata.trade.util.exception.UnprocessableEntityException;

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
					|| error instanceof ExpiredJwtException || error instanceof SignatureException
					|| error instanceof MalformedJwtException
		) {
			status = HttpStatus.UNAUTHORIZED;
			errorMap.put("code", ((ApiException) error).getErrorCode());
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

			Map<String, String> errors = new HashMap<>();
			((WebExchangeBindException) error).getBindingResult()
					.getFieldErrors()
					.forEach(
							err -> {
								if (errors.containsKey(err.getField())) {
									errors.put(
											err.getField(), String.format("%s,%s", errors.get(err.getField()), err.getDefaultMessage())
									);
								} else {
									errors.put(err.getField(), err.getDefaultMessage());
								}
							}
					);

			status = HttpStatus.UNPROCESSABLE_ENTITY;
			errorMap.put("code", "VALIDATION_FAILED");
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

			status = HttpStatus.INTERNAL_SERVER_ERROR;
			errorMap.put("code", "INTERNAL_ERROR");
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
