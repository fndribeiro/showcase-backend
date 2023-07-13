package br.com.ribeiro.fernando.showcasebackend.ports.authentication.oauth2;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.ribeiro.fernando.showcasebackend.ports.authentication.cookies.CookieProvider;
import br.com.ribeiro.fernando.showcasebackend.ports.deserializers.OAuth2AuthorizationRequestDeserializer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
	
	private static final String oauth2RequestCookieName = "oauth2_request";
	private static final CookieProvider cookieProvider = new CookieProvider();
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private final Logger logger = LoggerFactory.getLogger(CookieOAuth2AuthorizationRequestRepository.class);
	
	static {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OAuth2AuthorizationRequest.class, new OAuth2AuthorizationRequestDeserializer());
        objectMapper.registerModule(module);
    }
    
	@Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
		return Arrays
			.asList(request.getCookies())
			.stream()
			.filter(cookie -> oauth2RequestCookieName.equals(cookie.getName()))
			.findFirst()
			.map(cookieProvider::decode)
			.map(this::deserialize)
			.orElse(null);
    }
	
	private OAuth2AuthorizationRequest deserialize(byte[] bytes) {
		
		try {
			
			return objectMapper.readValue(bytes, OAuth2AuthorizationRequest.class);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
		return null;
		
	}

    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
    	
        try {
        	
			String serializedAuthorizationRequest = objectMapper.writeValueAsString(authorizationRequest);
			
			Cookie oauth2Cookie = cookieProvider.create(oauth2RequestCookieName, serializedAuthorizationRequest);
			
			response.addCookie(oauth2Cookie);
			
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
        
    }

    public void removeAuthorizationRequestCookie(HttpServletRequest request, HttpServletResponse response) {
    	Arrays
			.asList(request.getCookies())
			.stream()
			.filter(cookie -> oauth2RequestCookieName.equals(cookie.getName()))
			.findFirst()
			.map(cookieProvider::delete)
			.ifPresent(response::addCookie);
    }

	@Override
	public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
		return loadAuthorizationRequest(request);
	}

}
