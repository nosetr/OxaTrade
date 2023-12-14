package com.oxaata.trade.security;

import java.util.function.Function;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Implementation of
 * {@link org.springframework.security.web.server.authentication.ServerAuthenticationConverter
 * ServerAuthenticationConverter} for bearer token.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@RequiredArgsConstructor
public class BearerTokenServerAuthConverter implements ServerAuthenticationConverter {

	private final JwtHandler jwtHandler;

	private static final String BEARER_PREFIX = "Bearer ";

	/**
	 * Get value of bearer token without "Bearer "-prexif:
	 */
	private static final Function<String, Mono<String>> getBearerValue = authValue -> Mono
			.justOrEmpty(authValue.substring(BEARER_PREFIX.length()));

	@Override
	public Mono<Authentication> convert(ServerWebExchange exchange) {
		return extractHeader(exchange) // first header value
				.flatMap(getBearerValue) // Get value of bearer token
				.flatMap(jwtHandler::check) // get VerificationResult or error exception
				.flatMap(UserAuthenticationBearer::create);
	}

	/**
	 * Return the first header value for the given header name, if any.
	 * 
	 * @autor           Nikolay Osetrov
	 * @since           0.1.0
	 * @param  exchange ServerWebExchange
	 * @return          Mono<String>
	 */
	private Mono<String> extractHeader(ServerWebExchange exchange) {
		return Mono.justOrEmpty(
				exchange.getRequest()
						.getHeaders()
						.getFirst(HttpHeaders.AUTHORIZATION)
		);
	}
}
