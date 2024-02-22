package com.nosetr.auth.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nosetr.auth.controller.OAuth2RestV1Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * Controller implementation für authentication with OAuth2.
 * TODO
 * https://medium.com/@vajithc/implementing-social-logins-with-react-and-spring-boot-using-oauth2-5e7004edec8b
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Tag(name = "OAuth2_V1", description = "APIs for users authentication and registration with OAuth2")
@RestController
@RequiredArgsConstructor
public class OAuth2RestV1ControllerImpl implements OAuth2RestV1Controller {

	//	private final OAuth2AuthorizedClientService clientService;
	private final ReactiveOAuth2AuthorizedClientService clientService;

	@Autowired
	private ReactiveClientRegistrationRepository clientRegistrationRepository;

	/**
	 * @autor             Nikolay Osetrov
	 * @since             0.1.0
	 * @param  provider
	 * @param  oauth2User
	 * @return
	 */
	@Override
	public Mono<String> loginSuccess(
			@PathVariable String provider, @AuthenticationPrincipal Mono<OAuth2User> oauth2User
	) {

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

	//	@GetMapping("/")
	//	public Mono<UserDetailsResponse> index(@AuthenticationPrincipal OAuth2User oauth2User) {
	//		
	//		String firstName = oauth2User.getAttribute("given_name");
	//    String lastName = oauth2User.getAttribute("family_name");
	//    String email = oauth2User.getAttribute("email");
	//    String phone = oauth2User.getAttribute("phone_number");
	//		
	//    UserDetailsResponse userDetailsResponse = new UserDetailsResponse(firstName, lastName, email, phone);
	//    return Mono.just(userDetailsResponse);
	//	}
	//
	//	@Getter
	//  private static class UserDetailsResponse {
	//      private final String firstName;
	//      private final String lastName;
	//      private final String email;
	//      private final String phone;
	//
	//      public UserDetailsResponse(String firstName, String lastName, String email, String phone) {
	//          this.firstName = firstName;
	//          this.lastName = lastName;
	//          this.email = email;
	//          this.phone = phone;
	//      }
	//  }
}
