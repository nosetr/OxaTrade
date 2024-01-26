package com.nosetr.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nosetr.auth.dto.EmailDto;
import com.nosetr.auth.dto.NewsletterDto;
import com.nosetr.auth.service.NewsletterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Controller for actions with users profile.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Tag(name = "Newsletter_V1", description = "APIs for newsletters")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/newsletter")
public class NewsletterControllerV1 {

	private final NewsletterService newsletterService;

	/**
	 * Controller for actions with newsletter.
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
	public Mono<NewsletterDto> saveNewEmail(@Valid @RequestBody EmailDto authRequestDto) {
		return newsletterService.saveEmail(authRequestDto);
	}
}
