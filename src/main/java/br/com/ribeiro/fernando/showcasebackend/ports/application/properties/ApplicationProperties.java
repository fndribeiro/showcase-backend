package br.com.ribeiro.fernando.showcasebackend.ports.application.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public record ApplicationProperties(String tokenSecret, List<String> allowedOrigins, String frontendBaseUrl) {}
