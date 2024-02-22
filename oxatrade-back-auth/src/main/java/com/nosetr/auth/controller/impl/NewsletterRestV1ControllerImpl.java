package com.nosetr.auth.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.nosetr.auth.controller.NewsletterRestV1Controller;
import com.nosetr.auth.dto.EmailDto;
import com.nosetr.auth.dto.NewsletterDto;
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


	/**
	 * Save new email for newsletter.
	 * 
	 * @autor                Nikolay Osetrov
	 * @since                0.1.0
	 * @param  newsletterDto
	 * @return               Mono<NewsletterDto>
	 */
	@Override
	public Mono<NewsletterDto> saveNewEmail(@Valid EmailDto emailDto) {
		return newsletterService.saveEmail(emailDto);
	}

}
