package com.nosetr.auth.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nosetr.auth.dto.EmailDto;
import com.nosetr.auth.dto.NewsletterDto;
import com.nosetr.auth.entity.NewsletterEntity;
import com.nosetr.auth.enums.ErrorEnum;
import com.nosetr.auth.mapper.NewsletterMapper;
import com.nosetr.auth.repository.NewsletterRepository;
import com.nosetr.auth.service.NewsletterService;
import com.nosetr.auth.util.exception.EntityAlreadyExistsException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Implementation of NewsletterService for newsletters actions.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NewsletterServiceImpl implements NewsletterService {

	private final NewsletterRepository newsletterRepository;
	private final NewsletterMapper newsletterMapper;

	@Override
	public Mono<NewsletterDto> saveEmail(EmailDto emailNewsletterDto) {

		NewsletterEntity newsletterEntity = newsletterMapper.map(emailNewsletterDto);
		String email = newsletterEntity.getEmail();

		return newsletterRepository.findByEmail(email)
				.map(Optional::of)
				.defaultIfEmpty(Optional.empty())
				.flatMap(optionalEmail -> {
					// Exception if email founded:
					if (optionalEmail.isPresent()) {
						return Mono.error(
								new EntityAlreadyExistsException(ErrorEnum.NEWS_EMAIL_ALREADY_EXISTS, email)
						);
					}
					return newsletterRepository.save(newsletterEntity)
							.map(newsletterMapper::map)
							.doOnSuccess(u -> {
								// Make log about new email registration.
								log.info("IN saveEmail - email: {} created", u);
							});
				});
	}

	@Override
	public Mono<NewsletterEntity> updateEmail(NewsletterDto newsletterDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
