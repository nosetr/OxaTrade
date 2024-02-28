package com.nosetr.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Swagger configurations.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.4
 */
@Configuration
public class SwaggerConfig {

	/**
	 * Implement snake_case for Swagger documentation.
	 * 
	 * @autor               Nikolay Osetrov
	 * @since               0.1.4
	 * @param  objectMapper
	 * @return
	 */
	@Bean
	public ModelResolver modelResolver(ObjectMapper objectMapper) {
		return new ModelResolver(objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE));
	}

	/**
	 * Make it possible, to add a bearer-key by swagger.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.4
	 * @return
	 */
	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.components(
						new Components()
								.addSecuritySchemes("bearer-key", getSecuritySchemesItem())
				)
				.addSecurityItem(new SecurityRequirement().addList("bearer-key"));
	}

	/**
	 * Helper
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.4
	 * @return
	 */
	private SecurityScheme getSecuritySchemesItem() {
		return new SecurityScheme()
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.bearerFormat("JWT");
	}
}
