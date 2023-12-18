package com.oxaata.auth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

	@GetMapping("/")
	public Mono<UserDetailsResponse> index(@AuthenticationPrincipal OAuth2User oauth2User) {
		
		String firstName = oauth2User.getAttribute("given_name");
    String lastName = oauth2User.getAttribute("family_name");
    String email = oauth2User.getAttribute("email");
    String phone = oauth2User.getAttribute("phone_number");
		
    UserDetailsResponse userDetailsResponse = new UserDetailsResponse(firstName, lastName, email, phone);
    return Mono.just(userDetailsResponse);
	}

	@Getter
  private static class UserDetailsResponse {
      private final String firstName;
      private final String lastName;
      private final String email;
      private final String phone;

      public UserDetailsResponse(String firstName, String lastName, String email, String phone) {
          this.firstName = firstName;
          this.lastName = lastName;
          this.email = email;
          this.phone = phone;
      }
  }
	
	@GetMapping("/about")
  public String getAboutPage() {
     return "WebFlux OAuth example";
  }
	
}
