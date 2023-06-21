package br.com.ribeiro.fernando.showcasebackend.ports.spring.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(authorizeHttpRequests -> {
				authorizeHttpRequests
					.requestMatchers("/actuator/health")
					.permitAll();
			});
		
		return http.build();
	}

}
