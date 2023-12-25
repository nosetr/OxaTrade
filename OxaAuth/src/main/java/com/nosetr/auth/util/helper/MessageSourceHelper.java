package com.nosetr.auth.util.helper;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * With this helper you can easily use it in the enum to get messages in the
 * following way:
 * <p>
 * {@code MessageSourceHelper.getMessage(key, arguments, locale);}
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Component
public class MessageSourceHelper {

	@Autowired
	public MessageSource injectedMessageSource;

	public static MessageSource messageSource;

	/**
	 * Main method.
	 * 
	 * @autor             Nikolay Osetrov
	 * @since             0.1.0
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
		return messageSource.getMessage(messageKey, arguments, locale);
	}

	/**
	 * Method with {@code Locale.ENGLISH} as standard and without arguments.
	 * 
	 * @autor            Nikolay Osetrov
	 * @since            0.1.0
	 * @param  message
	 * @param  arguments
	 * @return
	 */
	public static String getMessage(String message) {

		Locale locale = LocaleContextHolder.getLocale();

		return messageSource.getMessage(message, null, locale);
	}

	/**
	 * Method needs to be executed after dependency injection.
	 * 
	 * @autor Nikolay Osetrov
	 * @since 0.1.0
	 */
	@PostConstruct
	public void postConstruct() {
		messageSource = injectedMessageSource;
	}
}
