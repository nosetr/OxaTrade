package com.nosetr.auth.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.nosetr.auth.entity.NewsletterEntity;

import reactor.core.publisher.Mono;

/**
 * Repository for newsletter-table.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Repository
public interface NewsletterRepository extends R2dbcRepository<NewsletterEntity, String> {
	Mono<NewsletterEntity> findByEmail(String email);
}
