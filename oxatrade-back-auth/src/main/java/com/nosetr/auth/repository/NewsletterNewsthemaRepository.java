package com.nosetr.auth.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.nosetr.auth.entity.NewsletterNewsthemaEntity;

import reactor.core.publisher.Flux;

/**
 * Repository for association table newsletter_newsthema
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Repository
interface NewsletterNewsthemaRepository extends R2dbcRepository<NewsletterNewsthemaEntity, Void> {

	Flux<NewsletterNewsthemaEntity> findByEmailId(UUID emailId);

	Flux<NewsletterNewsthemaEntity> findByThemaId(Long themaId);

	@Query("""
			SELECT *
			FROM newsletter_newsthema
			JOIN newsletter ON newsletter_newsthema.email = newsletter.email
			JOIN newsthema ON newsletter_newsthema.thema_id = newsthema.id
			""")
	Flux<NewsletterNewsthemaEntity> findNewsletterAndNewsthemaJoin();

}
