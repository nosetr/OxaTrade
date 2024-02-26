package com.nosetr.auth.dto;

import com.nosetr.library.annotation.ValidEmail;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO to send email's
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.4
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MailObjectDto {

	@NotBlank(message = "{validation.required.email}")
	@ValidEmail
	private String to;
	private String recipientName;
	private String subject;
	private String text;
	private String senderName;
	private String templateEngine;
}
