package com.oxaata.trade.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * The OAuth2AuthenticationConverter is a class that implements the Spring
 * Security Converter interface to convert an OAuth2UserRequest into an
 * OAuth2User. It allows you to customize how the user information is extracted
 * from the OAuth 2.0 provider's response and how the user details are
 * represented in your application.
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Slf4j
public class OAuth2AuthenticationConverter implements ServerAuthenticationConverter {
	
	@Override
  public Mono<Authentication> convert(ServerWebExchange exchange) {
      // Your custom logic to extract user details from the request or exchange
      // Example: Extract user details from OAuth2User and create an Authentication token
//      return Mono.just(new UsernamePasswordAuthenticationToken("username", "password"));
      
      
      return exchange.getPrincipal()
      	.filter(principal -> principal instanceof OAuth2AuthenticationToken)
      	.cast(OAuth2AuthenticationToken.class)
      	.map(oauthToken -> {
      		log.trace("Load user {}", oauthToken.getCredentials());
          // Hier kannst du die Benutzerdetails aus dem OAuth2AuthenticationToken extrahieren
          String userId = oauthToken.getName(); // Hier wird die Facebook-Benutzer-ID angenommen

          // Du könntest weitere Informationen wie E-Mail, Name usw. extrahieren
          String email = oauthToken.getPrincipal().getAttribute("email");
          String name = oauthToken.getPrincipal().getAttribute("name");

          // Erstelle eine benutzerdefinierte Authentication-Token
          return new UsernamePasswordAuthenticationToken(userId, null, oauthToken.getAuthorities());
      });
      
      
  }
	
//
//	/**
//	 * This method is called by Spring Security to convert an OAuth2UserRequest into
//	 * an OAuth2User.
//	 * It extracts the access token and additional parameters from the user request.
//	 * It calls the getAuthorities method to obtain the user's authorities.
//	 * It creates an OAuth2User instance using the DefaultOAuth2User class, which is
//	 * a default implementation provided by Spring Security. This instance includes
//	 * user authorities, attributes, and the user's name attribute.
//	 * The method returns the OAuth2User instance.
//	 */
//	@Override
//	public OAuth2User convert(OAuth2UserRequest userRequest) {
//		OAuth2AccessToken accessToken = userRequest.getAccessToken();
//		Map<String, Object> attributes = userRequest.getAdditionalParameters();
//
//		Collection<? extends GrantedAuthority> authorities = getAuthorities(attributes);
//
//		// Extract user attributes from OAuth2UserRequest and build OAuth2User instance
//		String userNameAttributeName = userRequest.getClientRegistration()
//				.getProviderDetails()
//				.getUserInfoEndpoint()
//				.getUserNameAttributeName();
//		OAuth2User oAuth2User = new DefaultOAuth2User(authorities, attributes, userNameAttributeName);
//
//		return oAuth2User;
//	}
//
//	/**
//	 * This method is responsible for extracting authorities (roles) from the user
//	 * attributes. In this example, it checks if the user attributes contain a key
//	 * called "roles."
//	 * If the "roles" key is found, it attempts to cast the corresponding value to a
//	 * Collection<String> and then maps it to a collection of SimpleGrantedAuthority
//	 * instances.
//	 * The method returns the collection of authorities.
//	 * 
//	 * @autor             Nikolay Osetrov
//	 * @since             0.1.0
//	 * @param  attributes
//	 * @return
//	 */
//	private Collection<? extends GrantedAuthority> getAuthorities(Map<String, Object> attributes) {
//		// Customize how authorities are extracted from user attributes
//		// In this example, authorities are extracted from a key called "roles"
//		if (attributes.containsKey("roles")) {
//			Object rolesObject = attributes.get("roles");
//			if (rolesObject instanceof Collection) {
//				// Check if the object is a Collection
//				Collection<?> rolesCollection = (Collection<?>) rolesObject;
//				// Check if all elements in the Collection are of type String
//				if (
//					rolesCollection.stream()
//							.allMatch(element -> element instanceof String)
//				) {
//					// Cast the Collection to Collection<String>
//					return rolesCollection.stream()
//							.map(Object::toString)
//							.map(SimpleGrantedAuthority::new)
//							.collect(Collectors.toList());
//				}
//			}
//		}
//		return Collections.emptyList();
//	}

}
