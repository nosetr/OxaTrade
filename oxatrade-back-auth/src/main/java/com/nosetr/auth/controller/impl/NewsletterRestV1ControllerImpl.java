package com.nosetr.auth.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nosetr.auth.controller.NewsletterRestV1Controller;
import com.nosetr.auth.dto.EmailRequestDto;
import com.nosetr.auth.dto.NewsletterDto;
import com.nosetr.auth.service.EmailService;
import com.nosetr.auth.service.NewsletterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Controller implementation for actions with newsletter.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@RestController
@RequiredArgsConstructor
public class NewsletterRestV1ControllerImpl implements NewsletterRestV1Controller {

	private final NewsletterService newsletterService;
	private final EmailService emailService;
	
	@Override
	@ResponseStatus(code = HttpStatus.OK)
	public  Mono<Void> sendTestEmail() {
		return emailService.sendSimpleMessage(
				"osetrov.n@gmail.com", "osetrov.n@oxaata.de", "proba", "proba-text");
	}

	/**
	 * Save new email for newsletter.
	 * 
	 * @autor                Nikolay Osetrov
	 * @since                0.1.0
	 * @param  newsletterDto
	 * @return               Mono<NewsletterDto>
	 */
	@Override
	public Mono<NewsletterDto> saveNewEmail(@Valid EmailRequestDto emailRequestDto) {
		return newsletterService.saveEmail(emailRequestDto);
	}


}
