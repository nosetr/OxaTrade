package com.nosetr.library.util.helper;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * With this helper you can easily use it in the enum to get messages in the
 * following way:
 * <p>
 * {@code MessageSourceHelper.getMessage(key, arguments, locale);}
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.3
 */
@Component
public class MessageSourceHelper {

	@Autowired
	@Qualifier("messageLibSource")
	public MessageSource injectedMessageSource;

	@Qualifier("messageLibSource")
	public static MessageSource messageSource;

	/**
	 * Main method.
	 * 
	 * @autor             Nikolay Osetrov
	 * @since             0.1.3
	 * @param  messageKey the message code to look up, e.g. 'calculator.noRateSet'.
	 *                    MessageSource users are encouraged to base message names
	 *                    on qualified class or package names, avoiding potential
	 *                    conflicts and ensuring maximum clarity.
	 * @param  arguments  an array of arguments that will be filled in for params
	 *                    within the message (params look like "{0}", "{1,date}",
	 *                    "{2,time}" within a message), or {@code null} if none.
	 * @param  locale     the locale in which to do the lookup.
	 * @return            String
	 */
	public static String getMessage(String messageKey, Object[] arguments, Locale locale) {
		return setMessageSource().getMessage(messageKey, arguments, locale); // TODO to remove if BEAN working
		//		return messageSource.getMessage(messageKey, arguments, locale);
	}

	/**
	 * Method with {@code Locale.ENGLISH} as standard and without arguments.
	 * 
	 * @autor            Nikolay Osetrov
	 * @since            0.1.3
	 * @param  message
	 * @param  arguments
	 * @return
	 */
	public static String getMessage(String message) {

		Locale locale = LocaleContextHolder.getLocale();

		return setMessageSource().getMessage(message, null, locale); // TODO to remove if BEAN working
		//		return messageSource.getMessage(message, null, locale);
	}

	/**
	 * To set a folder with messages.
	 * TODO to remove if {@code @Bean} from
	 * {@link com.nosetr.library.configMessageLibConfig} working
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.3
	 * @return
	 */
	private static MessageSource setMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:msg/messages");
		messageSource.setDefaultLocale(Locale.ROOT);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * Method needs to be executed after dependency injection.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.3
	 */
	@PostConstruct
	public void postConstruct() {
		messageSource = injectedMessageSource;
	}
}
