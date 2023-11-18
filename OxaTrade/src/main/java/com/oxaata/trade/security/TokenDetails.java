package com.oxaata.trade.security;

import java.util.Date;

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
	private Long userId;
	// Bearer Token
	private String token;
	//createdDate
	private Date issuedAt;
	//expirationDate
	private Date expiresAt;
}
