package com.nosetr.auth.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @autor Nikolay Osetrov
 * @since 0.1.4
 * @see   https://www.baeldung.com/spring-email
 * @see   https://github.com/eugenp/tutorials/blob/master/spring-web-modules/spring-mvc-basics-2/src/main/java/com/baeldung/spring/configuration/EmailConfiguration.java
 */
@Configuration
public class EMailConfig {

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private int port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private boolean auth;

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private boolean starttls;

	@Value("${spring.mail.properties.mail.smtp.transport.protocol}")
	private String protocol;

	@Value("${spring.mail.properties.mail.smtp.debug}")
	private boolean debug;

	/**
	 * Spring Mail Server Properties.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.4
	 * @return
	 */
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);

		mailSender.setUsername(username);
		mailSender.setPassword(password);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", protocol);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.debug", debug);

    // Enable SSL/TLS
    props.put("mail.smtp.ssl.enable", "true"); // Enable SSL
    props.put("mail.smtp.ssl.trust", host); // Trust server
    props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Set TLS protocol version

		return mailSender;
	}
}
