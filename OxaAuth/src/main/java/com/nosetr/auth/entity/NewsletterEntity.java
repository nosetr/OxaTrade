package com.nosetr.auth.entity;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for newsletter-table.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.1
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("newsletter")
public class NewsletterEntity {

	@Id
	private String email;
	private boolean enabled;
	
	@CreationTimestamp
	private LocalDateTime lastUpdate;

	/*
	 * see: https://www.baeldung.com/jpa-many-to-many
	 */
	@ManyToMany
	@JoinTable(
		  name = "newsletter_newsthema", 
		  joinColumns = @JoinColumn(name = "email"), 
		  inverseJoinColumns = @JoinColumn(name = "thema_id"))
	private Set<NewsthemaEntity> themenEntities;
}
