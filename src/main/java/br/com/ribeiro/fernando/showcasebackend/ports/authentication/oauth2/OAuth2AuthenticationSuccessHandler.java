package br.com.ribeiro.fernando.showcasebackend.ports.authentication.oauth2;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ribeiro.fernando.showcasebackend.ports.application.properties.ApplicationProperties;
import br.com.ribeiro.fernando.showcasebackend.ports.authentication.token.TokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final CookieOAuth2AuthorizationRequestRepository oauth2CookieAuthorizationRequestRepository;
	private final TokenProvider tokenProvider;
	private final ApplicationProperties applicationProperties;
	
	public OAuth2AuthenticationSuccessHandler(
			CookieOAuth2AuthorizationRequestRepository oauth2CookieAuthorizationRequestRepository,
			TokenProvider tokenProvider,
			ApplicationProperties applicationProperties
		) {
		
		this.oauth2CookieAuthorizationRequestRepository = oauth2CookieAuthorizationRequestRepository;
		this.tokenProvider = tokenProvider;
		this.applicationProperties = applicationProperties;
	}

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
        String targetUrl = determineTargetUrl(request, response, authentication);
        
        super.clearAuthenticationAttributes(request);
        
        oauth2CookieAuthorizationRequestRepository.removeAuthorizationRequestCookie(request, response);
        
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
        
    }
	
	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		String token = tokenProvider.create(authentication);
		
		String url = applicationProperties
			.frontendBaseUrl()
			.concat("/authentication");
		
		return UriComponentsBuilder
				.fromUriString(url)
				.queryParam("token", token)
				.build()
				.toUriString();
	}

}
