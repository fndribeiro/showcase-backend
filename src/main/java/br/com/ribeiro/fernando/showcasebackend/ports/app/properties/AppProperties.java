package br.com.ribeiro.fernando.showcasebackend.ports.app.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record AppProperties(String tokenSecret, List<String> allowedOrigins) {}
