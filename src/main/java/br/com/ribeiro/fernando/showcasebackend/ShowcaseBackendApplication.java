package br.com.ribeiro.fernando.showcasebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.ribeiro.fernando.showcasebackend.ports.application.properties.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class ShowcaseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowcaseBackendApplication.class, args);
	}

}
