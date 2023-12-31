package br.com.ribeiro.fernando.showcasebackend.ports.authentication.token;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import br.com.ribeiro.fernando.showcasebackend.ports.application.properties.ApplicationProperties;
import br.com.ribeiro.fernando.showcasebackend.ports.repositories.users.AdminUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {
	
	private static final Long expiration = Duration
			.of(7, ChronoUnit.DAYS)
			.toMillis();
	
	private static final String issuer = "https://showcase-backend-zojn.onrender.com";
	
	private final ApplicationProperties appProperties;
	private final AdminUserRepository adminUserRepository;
	
	public TokenProvider(ApplicationProperties appProperties, AdminUserRepository adminUserRepository) {
		this.appProperties = appProperties;
		this.adminUserRepository = adminUserRepository;
	}

	public String create(Authentication authentication) {
		
		byte[] tokenSecret = appProperties
				.tokenSecret()
				.getBytes();
		
		SecretKey secretKey = Keys.hmacShaKeyFor(tokenSecret);
		
		DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
		
		var name = (String) user.getAttribute("name");
		
		var email = (String) user.getAttribute("email");
		
		boolean isAdmin = adminUserRepository
				.findByEmail(email)
				.isPresent();
		
		var issuedAt = new Date();

		return Jwts
				.builder()
				.setIssuer(issuer)
				.setIssuedAt(issuedAt)
				.setSubject(email)
				.setExpiration(new Date(issuedAt.getTime() + expiration))
				.claim("name", name)
				.claim("email", email)
				.claim("admin", isAdmin)
				.signWith(secretKey)
				.compact();
	}
	
	public Claims validate(String bearerToken) {

		if (bearerToken.startsWith("Bearer ")) {
			
			String token = bearerToken.substring(7, bearerToken.length());
			
			byte[] tokenSecret = appProperties
					.tokenSecret()
					.getBytes();
			
			SecretKey secretKey = Keys.hmacShaKeyFor(tokenSecret);
			
			return Jwts
					.parserBuilder()
					.setSigningKey(secretKey)
					.build()
					.parseClaimsJws(token)
					.getBody();
			
		}
		
		throw new IllegalArgumentException("Invalid token type. Please provide Bearer token.");

	}

}
