package com.nosetr.library.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import lombok.extern.slf4j.Slf4j;

/**
 * Instead of hard coding the messages in the class level, we might want to
 * retrieve the messages from a property file.
 * <p>The corresponding messages for those keys are present in a property file
 * name {@code messages.properties} placed under {@code src/main/resources}.
 * TODO make it working!
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.3
 * @see https://www.vinsguru.com/spring-webflux-validation/
 */
@Slf4j
@Configuration
public class MessageLibConfig {

	@Bean(name = "messageLibSource")
	public MessageSource messageLibSource() {
		log.info("*************messageLibSource is called!!!*****************");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:msg/messages");
		messageSource.setDefaultLocale(Locale.ROOT);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
