package com.oxaata.trade.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * The OAuth2Config class in the provided example is responsible for configuring
 * OAuth 2.0 client registration details for your Spring WebFlux application. It
 * uses the ClientRegistrationRepository to define the OAuth 2.0 client
 * registration information, such as client ID, client secret, authorization and
 * token URIs, and other necessary details.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Configuration
@EnableWebFluxSecurity
public class OAuth2Config {

	//Values from config-file:
	@Value("${spring.security.oauth2.client.registration.google.client-id}")
	private String googleClientId;

	@Value("${spring.security.oauth2.client.registration.google.client-secret}")
	private String googleClientSecret;

	@Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
	private String googleRedirectUri;

	@Value("${spring.security.oauth2.client.registration.facebook.client-id}")
	private String facebookClientId;

	@Value("${spring.security.oauth2.client.registration.facebook.client-secret}")
	private String facebookClientSecret;

	@Value("${spring.security.oauth2.client.registration.facebook.redirect-uri}")
	private String facebookRedirectUri;

	/**
	 * Creates a bean of type ClientRegistrationRepository, which is responsible for
	 * managing OAuth 2.0 client registrations. Uses
	 * InMemoryReactiveClientRegistrationRepository to store client registration
	 * details in memory. In a production scenario, you might use a persistent
	 * storage mechanism. Defines a single client registration for Google as an
	 * example (ClientRegistration.withRegistrationId("google")).
	 * Sets various properties for the Google OAuth 2.0 client, such as client ID,
	 * client secret, redirect URI, scope, authorization URI, token URI, user info
	 * URI, user name attribute name, and client name.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return InMemoryReactiveClientRegistrationRepository
	 */
	@Bean
	ReactiveClientRegistrationRepository clientRegistrationRepository() {
		ClientRegistration googleRegistration = googleClientRegistration();
		ClientRegistration facebookRegistration = facebookClientRegistration();
		// Here can be added another OAuth 2.0-Clients

		return new InMemoryReactiveClientRegistrationRepository(googleRegistration, facebookRegistration);
	}

	/**
	 * OAuth 2.0-Facebook-Client
	 * <p>For Facebook:
	 * <a href="https://developers.facebook.com/">Go to the Facebook for Developers
	 * website.</a></br>
	 * Create a new app and fill in the required details.</br>
	 * Generate an App ID and App Secret.</br>
	 * Add http://localhost:8080/login/oauth2/code/facebook as the "Valid OAuth
	 * Redirect URIs" for local development.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return ClientRegistration
	 */
	private ClientRegistration facebookClientRegistration() {
		return ClientRegistration.withRegistrationId("facebook")
				.clientId(facebookClientId)
				.clientSecret(facebookClientSecret)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri(facebookRedirectUri)
				.scope("email")
				.authorizationUri("https://www.facebook.com/v12.0/dialog/oauth")
				.tokenUri("https://graph.facebook.com/v12.0/oauth/access_token")
				.userInfoUri("https://graph.facebook.com/me?fields=id,name,email")
				.userNameAttributeName("id")
				.clientName("Facebook")
				.build();
	}

	/**
	 * OAuth 2.0-Google-Client
	 * <p>For Google:<br/>
	 * <a href="https://console.cloud.google.com">Go to the Google Developers
	 * Console.</a><br/>
	 * Create a new project and enable the “Google+ API” in the API Library.<br/>
	 * Navigate to the “Credentials” tab and create a new OAuth client ID.<br/>
	 * Specify the authorized redirect URI as
	 * http://localhost:8080/login/oauth2/code/google for local development.
	 * 
	 * @autor  Nikolay Osetrov
	 * @since  0.1.0
	 * @return ClientRegistration
	 */
	private ClientRegistration googleClientRegistration() {
		return ClientRegistration
				.withRegistrationId("google")
				.clientId(googleClientId)
				.clientSecret(googleClientSecret)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				// The URI where the OAuth 2.0 provider should redirect after the user grants or denies permission:
				.redirectUri(googleRedirectUri)
				.scope("openid", "profile", "email")
				.authorizationUri("https://accounts.google.com/o/oauth2/auth")
				.tokenUri("https://accounts.google.com/o/oauth2/token")
				.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
				// The attribute name that represents the user's name in the user information returned by the OAuth 2.0 provider:
				.userNameAttributeName("email")
				.clientName("Google")
				.build();
	}
	
	/**
	 * WebClient for accessing protected resources
	 * 
	 * @autor                         Nikolay Osetrov
	 * @since                         0.1.0
	 * @param  clientRegistrationRepo
	 * @param  authorizedClientRepo
	 * @return                        WebClient
	 */
	@Bean
	public WebClient webClient(
			ReactiveClientRegistrationRepository clientRegistrationRepo,
			ServerOAuth2AuthorizedClientRepository authorizedClientRepo
	) {
		ServerOAuth2AuthorizedClientExchangeFilterFunction filter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
				clientRegistrationRepo, authorizedClientRepo
		);

		return WebClient.builder()
				.filter(filter)
				.build();
	}
}
