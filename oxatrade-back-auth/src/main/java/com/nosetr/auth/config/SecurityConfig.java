package com.nosetr.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import com.nosetr.auth.security.AuthenticationManager;
import com.nosetr.auth.security.BearerTokenServerAuthConverter;
import com.nosetr.auth.security.JwtHandler;

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
public class SecurityConfig {

	//Value from config-file:
	@Value("${oxa.jwt.secret}")
	private String secret;

	@Autowired
	private ReactiveClientRegistrationRepository clientRegistrationRepository;

	/*
	 * Array of routes with public access for registration and login
	 */
	private final String[] publicRoutes = {
			"/v1/auth/register",
			"/v1/auth/login",
			"/login/**",
			"/v1/newsletter"
	};
	
	/*
	 * Special routes for swagger
	 */
	private final String[] swaggerRouteStrings = {
			"/swagger",
			"/webjars/swagger-ui/**",
			"/api-docs/**"
	};

	/*
	 * Array of routes with access for users with role "USER"
	 */
	private final String[] usersRoutes = { "/v1/secure/**" };

//	@Bean
//	public ServerOAuth2AuthorizationRequestResolver authorizationRequestResolver(
//			ReactiveClientRegistrationRepository clientRegistrationRepository
//	) {
//		return new ServerOAuth2AuthorizationRequestResolver(
//				clientRegistrationRepository, ServerWebExchangeMatchers.pathMatchers("/login/oauth2/code/{registrationId}")
//		);
//	}

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
				// This configuration disables CSRF protection and allows all requests to access
				// resources without requiring authorization or authentication, thus eliminating
				// the need for a password (should be enabled in production):
				.csrf(csrf -> csrf.disable())
				.authorizeExchange(
						exchange -> exchange.pathMatchers(HttpMethod.OPTIONS)
								.permitAll()
								.pathMatchers(publicRoutes) // make routes from publicRoutes public
								.permitAll()
								.pathMatchers(swaggerRouteStrings) // swagger routes
								.permitAll()
								.pathMatchers(usersRoutes)
								.hasRole("USER")
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
				.oauth2Login(Customizer.withDefaults())

				//				.oauth2Login(
				//						oauth2LoginSpec -> oauth2LoginSpec
				//								.clientRegistrationRepository(clientRegistrationRepository)
				//								.authorizationRequestResolver(authorizationRequestResolver(clientRegistrationRepository))
				//								.authenticationConverter(googleAuthenticationConverter())
				//				)

				//				.oauth2Login(
				//						oauth2Login -> oauth2Login
				//								// store client registration details in memory:
				//								.clientRegistrationRepository(clientRegistrationRepository)
				//								// convert an OAuth2UserRequest into an OAuth2User
				//								.authenticationConverter(new OAuth2AuthenticationConverter())
				//								// The default Authorization Response redirection endpoint is /login/oauth2/code/{registrationId}
				//								.authenticationMatcher(
				//										new PathPatternParserServerWebExchangeMatcher("/v1/oauth2/{registrationId}")
				//								)
				//				)
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
