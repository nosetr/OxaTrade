package com.nosetr.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * TODO
 * https://medium.com/@vajithc/implementing-social-logins-with-react-and-spring-boot-using-oauth2-5e7004edec8b
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@RestController
@RequestMapping("/api/v1/oauth2")
public class OAuth2ControllerV1 {

//	private final OAuth2AuthorizedClientService clientService;
	private final ReactiveOAuth2AuthorizedClientService clientService;

	public OAuth2ControllerV1(ReactiveOAuth2AuthorizedClientService clientService) {
		this.clientService = clientService;
	}
	
	@Autowired
	private ReactiveClientRegistrationRepository clientRegistrationRepository;

	@GetMapping("/{provider}")
	public Mono<String> loginSuccess(@PathVariable String provider, @AuthenticationPrincipal Mono<OAuth2User> oauth2User) {
		
		return oauth2User
        .map(OAuth2User::getName)
        .map(name -> String.format("Hi, %s", name));
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
//		if (authentication instanceof OAuth2AuthenticationToken) {
//			return Mono.just(new RedirectView(authentication.getPrincipal().toString()));
//		}
		
//		Mono<OAuth2AuthorizedClient> client = clientService.loadAuthorizedClient(
//				authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName()
//		);

		// Handle storing the user information and token, then redirect to a successful login page
		// For example, store user details in your database and generate a JWT token for further authentication.
//		String userEmail = (String) client.getPrincipalAttributes().get("email");
//    String provider2 = authenticationToken.getAuthorizedClientRegistrationId();

//    Optional<User> existingUser = userRepository.findByEmail(userEmail);
//    if (!existingUser.isPresent()) {
//      User user = new User();
//      user.setEmail(userEmail);
//      user.setProvider(provider);
//      userRepository.save(user);
//    }
		
//		return client;
		
//		return clientRegistrationRepository.findByRegistrationId(provider);
	}
}
