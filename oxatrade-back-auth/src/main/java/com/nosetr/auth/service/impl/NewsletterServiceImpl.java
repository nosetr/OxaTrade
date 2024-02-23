package com.nosetr.auth.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nosetr.auth.dto.EmailDto;
import com.nosetr.auth.dto.NewsletterDto;
import com.nosetr.auth.entity.NewsletterEntity;
import com.nosetr.auth.entity.NewsthemaEntity;
import com.nosetr.auth.mapper.NewsletterMapper;
import com.nosetr.auth.repository.NewsletterRepository;
import com.nosetr.auth.repository.NewsthemaRepository;
import com.nosetr.auth.service.NewsletterService;
import com.nosetr.library.enums.ErrorEnum;
import com.nosetr.library.exception.EntityAlreadyExistsException;

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
	private final NewsthemaRepository newsthemaRepository;
	private final NewsletterMapper newsletterMapper;

	@Override
	public Mono<NewsletterDto> saveEmail(EmailDto emailDto) {

		//		NewsletterEntity newsletterEntity = newsletterMapper.map(emailDto);
		String email = emailDto.getEmail();
		// TODO Default theme if not exist:
		Long themaId = (emailDto.getNewstheme() == null) ? 1L : emailDto.getNewstheme();

		return newsletterRepository
				.findByEmail(email)
				.map(Optional::of)
				.defaultIfEmpty(Optional.empty())
				.flatMap(optionalEmail -> {
					// Exception if email founded:
					if (optionalEmail.isPresent()) {
						return Mono.error(
								new EntityAlreadyExistsException(ErrorEnum.NEWS_EMAIL_ALREADY_EXISTS, email)
						);
					}
					NewsletterEntity newsletterEntity = new NewsletterEntity();
					newsletterEntity.setEmail(email);
          newsletterEntity.setEnabled(false);
          newsletterEntity.setLastUpdate(LocalDateTime.now());

					Mono<NewsthemaEntity> themaMono = newsthemaRepository.findOneById(themaId);
					log.info("IN saveEmail - themaMono: {}", themaMono);
					themaMono.subscribe(newsthemaEntity -> {
						// Here we can access the NewsthemaEntity
						newsletterEntity.addTheme(newsthemaEntity);
						log.info("IN saveEmail - newsletterEntity: {}", newsletterEntity);
					});

					return newsletterRepository.save(
							newsletterEntity
					)
							.doOnSuccess(u -> {
								// Make log about new email registration.
								log.info("IN saveEmail - email: {} created", newsletterEntity);
							})
							.map(newsletterMapper::map);
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

	//	see: https://spring.io/blog/2019/05/16/reactive-transactions-with-spring/
	//	final DatabaseClient db
	//
	//  TransactionalService(DatabaseClient db) {
	//    this.db = db;
	//  }
	//
	//  @Transactional
	//  Mono<Void> insertRows() {
	//
	//    return db.execute()
	//      .sql("INSERT INTO person (name, age) VALUES('Joe', 34)")
	//      .fetch().rowsUpdated()
	//      .then(db.execute().sql("INSERT INTO contacts (name) VALUES('Joe')")
	//      .then();
	//  }

}
