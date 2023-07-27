package br.com.ribeiro.fernando.showcasebackend.ports.authentication.configurations;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import br.com.ribeiro.fernando.showcasebackend.ports.application.properties.ApplicationProperties;
import br.com.ribeiro.fernando.showcasebackend.ports.authentication.filters.TokenValidationFilter;
import br.com.ribeiro.fernando.showcasebackend.ports.authentication.oauth2.CookieOAuth2AuthorizationRequestRepository;
import br.com.ribeiro.fernando.showcasebackend.ports.authentication.oauth2.OAuth2AuthenticationFailureHandler;
import br.com.ribeiro.fernando.showcasebackend.ports.authentication.oauth2.OAuth2AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfiguration {
	
	private final CookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository;
	private final OAuth2AuthenticationSuccessHandler oauth2CookieAuthorizationRequestRepository;
	private final OAuth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler;
	private final ApplicationProperties appProperties;
	private final TokenValidationFilter tokenValidationFilter;
	
	public SecurityConfiguration(
			CookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository, 
			OAuth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler,
			OAuth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler,
			ApplicationProperties appProperties,
			TokenValidationFilter tokenValidationFilter
		) {
		
		this.cookieOAuth2AuthorizationRequestRepository = cookieOAuth2AuthorizationRequestRepository;
		this.oauth2CookieAuthorizationRequestRepository = oauth2AuthenticationSuccessHandler;
		this.oauth2AuthenticationFailureHandler = oauth2AuthenticationFailureHandler;
		this.appProperties = appProperties;
		this.tokenValidationFilter = tokenValidationFilter;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.cors(cors -> {
				
				cors.configurationSource(new CorsConfigurationSource() {
					
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(appProperties.allowedOrigins());
						config.setAllowedMethods(List.of("*"));
						config.setAllowedHeaders(List.of("*"));
						return config;
					}
					
				});
				
			})
			.authorizeHttpRequests(authorizeHttpRequests -> {
				
				authorizeHttpRequests
					.requestMatchers(
							"/auth/**", 
							"/oauth2/**", 
							"/actuator/health", 
							"/v3/api-docs/**",
							"/swagger-ui/**",
							"/docs")
					.permitAll();
				
				authorizeHttpRequests
					.anyRequest()
					.authenticated();
				
			})
			.oauth2Login(oauth2Login -> {
				
				oauth2Login.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository));
				
				oauth2Login.successHandler(oauth2CookieAuthorizationRequestRepository);
				
				oauth2Login.failureHandler(oauth2AuthenticationFailureHandler);
				
			})
			.addFilterBefore(tokenValidationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

}
