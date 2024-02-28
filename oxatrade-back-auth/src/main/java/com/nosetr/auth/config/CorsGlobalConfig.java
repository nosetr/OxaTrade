package com.nosetr.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.nosetr.library.factories.YamlPropertySourceFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Enabling Cross Origin Requests for a RESTful Web Service
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@PropertySource(value = "classpath:config/oxa.yml", factory = YamlPropertySourceFactory.class)
@Configuration
@Slf4j
public class CorsGlobalConfig implements WebFluxConfigurer {

	private final long MAX_AGE_SECS = 3600;

	@Value("${oxatrade.cors.allowedOrigins}")
	private String[] allowedOrigins;

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		log.info("+++++++++++++++++++++ oxatrade.cors.allowedOrigins: ",allowedOrigins.toString());
		corsRegistry.addMapping("/**")
				.allowedOrigins(allowedOrigins)
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(MAX_AGE_SECS);
	}
}
