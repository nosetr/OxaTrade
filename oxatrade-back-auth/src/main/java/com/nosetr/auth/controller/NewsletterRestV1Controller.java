package com.nosetr.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nosetr.auth.dto.EmailDto;
import com.nosetr.auth.dto.NewsletterDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

/**
 * Controller interface for actions with newsletter.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Tag(name = "Newsletter_V1", description = "APIs for newsletters")
@RequestMapping("/api/v1/newsletter")
public interface NewsletterRestV1Controller {

	/**
	 * Save new email for newsletter.
	 * 
	 * @autor                Nikolay Osetrov
	 * @since                0.1.0
	 * @param  newsletterDto
	 * @return               Mono<NewsletterDto>
	 */
	@Operation(
			summary = "Save new email for newsletter.",
			tags = { "users_tag", "post_tag" }
	)
	@PostMapping
	public Mono<NewsletterDto> saveNewEmail(@Valid @RequestBody EmailDto emailDto);
}
