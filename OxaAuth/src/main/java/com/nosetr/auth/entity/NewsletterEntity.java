package com.nosetr.auth.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
	private UUID id;
	private String email;
	private boolean enabled;
	private LocalDateTime lastUpdate;

	/*
	 * The many-to-many relationship to NewsthemaEntity
	 */
	@Builder.Default
	private Set<NewsthemaEntity> newsthemen = new HashSet<>();

	/**
	 * Add theme by many-to-many.
	 * 
	 * @autor           Nikolay Osetrov
	 * @since           0.1.2
	 * @param themaMono
	 * @see             https://manerajona.medium.com/mapping-bidirectional-object-associations-using-mapstruct-ce49b1857604
	 */
	public void addTheme(NewsthemaEntity thema) {
		if (this.newsthemen == null) this.newsthemen = new HashSet<>();
		this.newsthemen.add(thema);
		thema.getEmails()
				.add(this);
	}

	/**
	 * Remove theme by many-to-many.
	 * 
	 * @autor         Nikolay Osetrov
	 * @since         0.1.2
	 * @param themeId
	 */
	public void removeTheme(Long themeId) {
		this.newsthemen.removeIf(
				theme -> theme.getId()
						.equals(themeId)
		);
	}
}
