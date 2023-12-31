package br.com.ribeiro.fernando.showcasebackend.ports.authentication.filters;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nimbusds.oauth2.sdk.util.StringUtils;

import br.com.ribeiro.fernando.showcasebackend.domain.entities.users.LoggedUser;
import br.com.ribeiro.fernando.showcasebackend.ports.authentication.token.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenValidationFilter extends OncePerRequestFilter {
	
	private final TokenProvider tokenProvider;
	
	public TokenValidationFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (StringUtils.isNotBlank(token)) {
			
			try {
				
				Claims claims = tokenProvider.validate(token);
				
				var name = (String) claims.get("name");
				var email = (String) claims.get("email");
				var isAdmin = (Boolean) claims.get("admin");
				
				var loggedUser = new LoggedUser(name, email, isAdmin);
				
				var authentication = new UsernamePasswordAuthenticationToken(loggedUser, null, null);
				
				SecurityContextHolder
					.getContext()
					.setAuthentication(authentication);
				
			} catch (IllegalArgumentException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException  e) {
				
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				
				response
					.getOutputStream()
					.print(e.getMessage());
				
				return;
				
			}
			
		}
		
		filterChain.doFilter(request, response);
	}
	
}
