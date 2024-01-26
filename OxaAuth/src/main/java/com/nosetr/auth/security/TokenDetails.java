package com.nosetr.auth.security;

import java.util.Date;
import java.util.UUID;

import com.nosetr.auth.service.SecurityService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for token transfer.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 * @see   SecurityService
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TokenDetails {
	private UUID userId;
	// Bearer Token
	private String token;
	//createdDate
	private Date issuedAt;
	//expirationDate
	private Date expiresAt;
}
