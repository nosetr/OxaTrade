package com.oxaata.trade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import com.oxaata.trade.security.AuthenticationManager;
import com.oxaata.trade.security.BearerTokenServerAuthConverter;
import com.oxaata.trade.security.JwtHandler;
import com.oxaata.trade.security.OAuth2AuthenticationConverter;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Custom configuration for spring-security
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Slf4j
@Configuration
//@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class WebSecurityConfig {

	//Value from config-file:
	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	private ReactiveClientRegistrationRepository clientRegistrationRepository;

	/*
	 * Array of routes with public access for registration and login
	 */
	private final String[] publicRoutes = { "/api/v1/auth/register", "/api/v1/auth/login",
			"/api/v1/oauth2/{registrationId}"
	};

	/**
	 * Defines a filter chain for public and authorization access.
	 * 
	 * @autor              Nikolay Osetrov
	 * @since              0.1.0
	 * @param  http        {@link org.springframework.security.config.web.server.ServerHttpSecurity
	 *                     ServerHttpSecurity}
	 * @param  authManager {@link AuthenticationManager}
	 * @return             {@link org.springframework.security.web.server.SecurityWebFilterChain
	 *                     SecurityWebFilterChain}
	 */
	@Bean
	public SecurityWebFilterChain filterChain(ServerHttpSecurity http, AuthenticationManager authManager) {

		return http
				// Disable CSRF for simplicity (should be enabled in production)
				.csrf(csrf -> csrf.disable())
				.authorizeExchange(
						exchange -> exchange.pathMatchers(HttpMethod.OPTIONS)
								.permitAll()
								.pathMatchers(publicRoutes) // make routes from publicRoutes public
								.permitAll()
								.anyExchange() // all other routes are not public
								.authenticated()
				)
				// throw exceptions
				.exceptionHandling(
						exceptionHandling -> exceptionHandling
								// customize how to request for authentication
								.authenticationEntryPoint((swe, e) -> {
									log.error("IN securityWebFilterChain - unauthorized error: {}", e.getMessage());
									return Mono.fromRunnable(
											() -> swe.getResponse()
													.setStatusCode(HttpStatus.UNAUTHORIZED)
									);
								})
								.accessDeniedHandler((swe, e) -> {
									log.error("IN securityWebFilterChain - access denied: {}", e.getMessage());
									return Mono.fromRunnable(
											() -> swe.getResponse()
													.setStatusCode(HttpStatus.FORBIDDEN)
									);
								})
				)
				// filter for all requests
				.addFilterAt(bearerAuthenticationFilter(authManager), SecurityWebFiltersOrder.AUTHENTICATION)
				// OAuth2:
				.oauth2Login(
						oauth2Login -> oauth2Login
								// store client registration details in memory:
								.clientRegistrationRepository(clientRegistrationRepository)
								// convert an OAuth2UserRequest into an OAuth2User
								.authenticationConverter(new OAuth2AuthenticationConverter())
								// The default Authorization Response redirection endpoint is /login/oauth2/code/{registrationId}
								.authenticationMatcher(
										new PathPatternParserServerWebExchangeMatcher("/api/v1/oauth2/{registrationId}")
								)
				)
				.build();
	}

	/**
	 * Filter for all requests with bearer token.
	 * 
	 * @autor              Nikolay Osetrov
	 * @since              0.1.0
	 * @param  authManager {@link AuthenticationManager}
	 * @return             {@link org.springframework.security.web.server.authentication.AuthenticationWebFilter
	 *                     AuthenticationWebFilter}
	 * @see                BearerTokenServerAuthConverter
	 * @see                JwtHandler
	 */
	private AuthenticationWebFilter bearerAuthenticationFilter(AuthenticationManager authManager) {

		// Creates an instance:
		AuthenticationWebFilter bearerAuthFilter = new AuthenticationWebFilter(authManager);

		bearerAuthFilter
				.setServerAuthenticationConverter(new BearerTokenServerAuthConverter(new JwtHandler(secret)));

		bearerAuthFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));

		return bearerAuthFilter;
	}
}
