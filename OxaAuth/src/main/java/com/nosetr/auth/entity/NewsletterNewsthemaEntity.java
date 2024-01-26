package com.nosetr.auth.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Association table without a separate ID column and use a composite key.
 * <p>Please note that this assumes the combination of email and thema_id is
 * unique, which is often the case for a <b>Many-to-Many</b> relationship.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("newsletter_newsthema")
public class NewsletterNewsthemaEntity {

	@Column("email")
	private String email;

	@Column("thema_id")
	private Long themaId;

}
