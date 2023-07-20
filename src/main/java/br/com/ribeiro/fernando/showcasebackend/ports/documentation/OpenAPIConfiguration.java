package br.com.ribeiro.fernando.showcasebackend.ports.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {
	
	private static final String description = 
			"Welcome to my backend API documentation."
			+ "<br>"
			+ "<br>"
			+ "This API is dedicated to Showcase Frontend APP usage only."
			+ "<br>"
			+ "<br>"
			+ "All endpoints are secured with JWT Bearer Authentication type. So all requests must provide a Bearer Authorization Header."
			+ "<br>"
			+ "<br>"
			+ "To generate a token user must be autenticated by Google with Oaut2h."
			+ "<br>"
			+ "<br>"
			+ "After success authentication backend will provide a new token to the frontend.";
	
	@Bean
	public OpenAPI customOpenAPI() {
		
		var info = new Info()
				.title("Fernando Ribeiro Showcase Backend Documentation")
				.description(description)
				.version("1.0");
		
		return new OpenAPI()
				.components(new Components())
				.info(info);
		
	}

}
