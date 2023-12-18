package com.oxaata.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {

		return http
				// This configuration disables CSRF protection and allows all requests to access
				// resources without requiring authorization or authentication, thus eliminating
				// the need for a password:
				.csrf(csrf -> csrf.disable())
				.authorizeExchange(
						exchange -> exchange.pathMatchers("/about")
								.permitAll()
								.anyExchange()
								.authenticated()
				)
				.oauth2Login(Customizer.withDefaults())
				.build();
	}
}
