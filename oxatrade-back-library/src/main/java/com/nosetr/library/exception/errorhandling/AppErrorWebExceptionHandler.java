package com.nosetr.library.exception.errorhandling;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Method to response for all exceptions.
 * <p><b>The important thing to note here is that you must set the
 * {@code @Order} value otherwise this implementation is skipped over and never
 * called!
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class AppErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

	public AppErrorWebExceptionHandler(
			AppErrorAttributes g, ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer
	) {
		super(g, new WebProperties.Resources(), applicationContext);
		super.setMessageWriters(serverCodecConfigurer.getWriters());
		super.setMessageReaders(serverCodecConfigurer.getReaders());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), request -> {
			var props = getErrorAttributes(request, ErrorAttributeOptions.defaults());

			return ServerResponse.status(
					Integer.parseInt(
							props.getOrDefault("status", 500)
									.toString()
					)
			)
					.contentType(MediaType.APPLICATION_JSON)
					.body(BodyInserters.fromValue(props.get("errors")));
		});
	}
}
