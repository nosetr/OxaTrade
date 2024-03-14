package com.nosetr.auth.service.impl;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.nosetr.auth.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Service implementation for send email's.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.4
 * @see   https://www.baeldung.com/spring-email
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender emailSender;

	private final SpringTemplateEngine thymeleafTemplateEngine;

	@Value("classpath:/mail-logo.png")
	private Resource resourceFile;

	/**
	 * Send a simple email message without any attachments.
	 * 
	 * @autor         Nikolay Osetrov
	 * @since         0.1.4
	 * @param from
	 * @param to
	 * @param subject
	 * @param text
	 */
	@Override
	public Mono<Void> sendSimpleMessage(String from, String to, String subject, String text) {

		return Mono.fromRunnable(() -> {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
		});
	}

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
	@Override
	public Mono<Void> sendMessageWithAttachment(
			String from, String to, String subject, String text, String pathToAttachment
	) {

		return Mono.fromRunnable(() -> {
			try {
				MimeMessage message = emailSender.createMimeMessage();
				// pass 'true' to the constructor to create a multipart message
				MimeMessageHelper helper = new MimeMessageHelper(message, true);

				helper.setFrom(from);
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(text);

				File file = new File(pathToAttachment);

				FileSystemResource resource = new FileSystemResource(file);
				helper.addAttachment(file.getName(), resource);

				emailSender.send(message);
			} catch (MessagingException e) {
				log.warn("Failed to send email with subject {}, due to {}", subject, e.getMessage());
				e.printStackTrace();
			}
		});
	}

	/**
	 * Send message using thymeleaf template.
	 * 
	 * @autor                     Nikolay Osetrov
	 * @since                     0.1.4
	 * @param  from
	 * @param  to
	 * @param  subject
	 * @param  templateModel
	 * @return
	 * @throws MessagingException
	 */
	@Override
	public Mono<Void> sendMessageUsingHtmlTemplate(
			String from, String to, String subject, Map<String, Object> templateModel
	) {

		return Mono.fromRunnable(() -> {
			Context thymeleafContext = new Context();
			thymeleafContext.setVariables(templateModel);

			String htmlBody = thymeleafTemplateEngine.process("template-thymeleaf.html", thymeleafContext);

			// send Html message:
			try {
				MimeMessage message = emailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				helper.setFrom(from);
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(htmlBody, true);
				// Image has to be referenced from Thymeleaf email's using CID notation:
				helper.addInline("attachment.png", resourceFile);
				emailSender.send(message);
			} catch (MessagingException e) {
				log.warn("Failed to send email with subject {}, due to {}", subject, e.getMessage());
				e.printStackTrace();
			}
		});
	}

}
