package com.nosetr.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

/**
 * Thymeleaf Configuration for email's templates.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.4
 */
@Configuration
public class ThymeleafConfig {

	@Value("${oxa.mail.templates.path}")
	private String mailTemplatesPath;

	/**
	 * Provide a template resolver to locate the template files directory.
	 * @return 
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.4
	 * @return
	 */
	@Bean
  public SpringResourceTemplateResolver thymeleafTemplateResolver() {
      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
      templateResolver.setPrefix(mailTemplatesPath);
      templateResolver.setSuffix(".html");
      templateResolver.setTemplateMode("HTML");
      return templateResolver;
  }

	/**
	 * The resolver we created earlier is injected automatically by Spring into the
	 * template engine factory method.
	 * 
	 * @autor                   Nikolay Osetrov
	 * @since                   0.1.4
	 * @param  templateResolver
	 * @return
	 */
  @Bean
  public SpringTemplateEngine thymeleafTemplateEngine() {
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(thymeleafTemplateResolver());

      templateEngine.setTemplateEngineMessageSource(emailMessageSource());
      return templateEngine;
  }

	/**
	 * Localization
	 * <p>Specify a MessageSource instance to the engine
	 * <p>Create resource bundles for each locale we support:
	 * <p>Example: src/main/resources/mailMessages_en_EN.properties
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.4
	 * @return
	 */
  @Bean
  public ResourceBundleMessageSource emailMessageSource() {
      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      messageSource.setBasename("mailMessages");
      return messageSource;
  }
}
