package com.oxaata.trade.util.exception.errorhandling;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandling{
//implements WebExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> hashMap = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(e ->{
			String field = ((FieldError) e).getField();
			String message = ((FieldError) e).getDefaultMessage();
			
			hashMap.put(field, message);
			
		});
		return hashMap;
	}

	
	
	
	
	private final ObjectMapper objectMapper;
	
//	@Override
//  @SneakyThrows
//  public Mono<Void> handle(final ServerWebExchange exchange, final Throwable throwable) {
//
//      if (throwable instanceof WebExchangeBindException validationEx) {
//          final Map<String, String> errors = getValidationErrors(validationEx);
//
//          exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
//          exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//
//          return writeResponse(exchange, objectMapper.writeValueAsBytes(errors));
//      } else {
//          return Mono.error(throwable);
//      }
//  }

  private Map<String, String> getValidationErrors(final WebExchangeBindException validationEx) {

      return validationEx.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,
              error -> Optional.ofNullable(error.getDefaultMessage()).orElse("")));
  }

  private Mono<Void> writeResponse(final ServerWebExchange exchange, final byte[] responseBytes) {

      return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(responseBytes)));
  }
}
