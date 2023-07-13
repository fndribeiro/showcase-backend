package br.com.ribeiro.fernando.showcasebackend.ports.authentication.oauth2;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private final CookieOAuth2AuthorizationRequestRepository oauth2CookieAuthorizationRequestRepository;
	
	public OAuth2AuthenticationFailureHandler(CookieOAuth2AuthorizationRequestRepository oauth2CookieAuthorizationRequestRepository) {
		this.oauth2CookieAuthorizationRequestRepository = oauth2CookieAuthorizationRequestRepository;
	}

	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
		
		String uri = UriComponentsBuilder
        		.fromUriString("https://localhost:8080/teste")
                .queryParam("error", exception.getLocalizedMessage())
                .build()
                .toUriString();
		
		oauth2CookieAuthorizationRequestRepository.removeAuthorizationRequestCookie(request, response);

        getRedirectStrategy().sendRedirect(request, response, uri);
    }

}
