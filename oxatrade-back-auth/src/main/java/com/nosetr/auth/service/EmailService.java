package com.nosetr.auth.service;

import java.util.Map;

import jakarta.mail.MessagingException;

/**
 * Service interface for send email's.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.4
 */
public interface EmailService {

	/**
	 * Send a simple email message without any attachments.
	 * 
	 * @autor         Nikolay Osetrov
	 * @since         0.1.4
	 * @param to
	 * @param subject
	 * @param text
	 */
	void sendSimpleMessage(String from, String to, String subject, String text);

	/**
	 * Sending email's with attachments.
	 * 
	 * @autor                  Nikolay Osetrov
	 * @since                  0.1.4
	 * @param from
	 * @param to
	 * @param subject
	 * @param text
	 * @param pathToAttachment
	 */
	void sendMessageWithAttachment(String from, String to, String subject, String text, String pathToAttachment);

	/**
	 * @autor                     Nikolay Osetrov
	 * @since                     0.1.4
	 * @param  from
	 * @param  to
	 * @param  subject
	 * @param  templateModel
	 * @throws MessagingException
	 */
	void sendMessageUsingHtmlTemplate(String from, String to, String subject, Map<String, Object> templateModel)
			throws MessagingException;

}
