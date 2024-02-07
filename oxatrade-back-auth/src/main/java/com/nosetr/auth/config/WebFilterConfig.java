package com.nosetr.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.WebFilter;

import reactor.util.context.Context;

/**
 * Configurations for WebFilter
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.3
 */
@Configuration
public class WebFilterConfig {

	public enum ContextKey {
		TRX_ID,
		PATH_URI
	}

	/**
	 * To introduce the transaction ID and path variables into the Webflux context,
	 * consider the following WebFilter configuration. As a @Bean with highest
	 * priority, the slf4jMdcFilter extracts the request's unique ID and path URI,
	 * incorporating them into the context. This ensures that subsequent processing
	 * stages, including the RequestLoggingAspect, can seamlessly access this
	 * enriched context for precise and comprehensive request handling.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.3
	 * @return
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	WebFilter slf4jMdcFilter() {
		return (exchange, chain) -> {
			String requestId = exchange.getRequest()
					.getId();
      String pathUri = exchange.getRequest().getPath().toString();
			return chain.filter(exchange)
					.contextWrite(
							Context.of(ContextKey.TRX_ID.name(), requestId)
									.put(
											ContextKey.PATH_URI.name(), pathUri
									)
					);
		};
	}
}
