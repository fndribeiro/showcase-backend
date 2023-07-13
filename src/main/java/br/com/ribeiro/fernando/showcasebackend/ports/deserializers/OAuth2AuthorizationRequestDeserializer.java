package br.com.ribeiro.fernando.showcasebackend.ports.deserializers;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class OAuth2AuthorizationRequestDeserializer extends StdDeserializer<OAuth2AuthorizationRequest> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4677632272390951174L;

	public OAuth2AuthorizationRequestDeserializer() {
        super(OAuth2AuthorizationRequest.class);
    }

	@Override
	public OAuth2AuthorizationRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		
		JsonNode node = p
				.getCodec()
				.readTree(p);

        String authorizationUri = node
        		.get("authorizationUri")
        		.asText();
        
        String clientId = node
        		.get("clientId")
        		.asText();
        
        String redirectUri = node
        		.get("redirectUri")
        		.asText();
        
        String state = node
        		.get("state")
        		.asText();
        
        Set<String> scopes = new HashSet<>();
        
        node
        	.get("scopes")
        	.forEach(scope -> scopes.add(scope.asText()));
        
        Map<String, Object> additionalParameters = new HashMap<>();
        
        node
        	.get("additionalParameters")
        	.fields()
        	.forEachRemaining(parameter -> additionalParameters.put(parameter.getKey(), parameter.getValue().asText()));
        
        Map<String, Object> attributes = new HashMap<>();
        
        node
    		.get("attributes")
    		.fields()
    		.forEachRemaining(parameter -> attributes.put(parameter.getKey(), parameter.getValue().asText()));
        
        return OAuth2AuthorizationRequest
        		.authorizationCode()
                .authorizationUri(authorizationUri)
                .clientId(clientId)
                .redirectUri(redirectUri)
                .scopes(scopes)
                .state(state)
                .additionalParameters(additionalParameters)
                .attributes(attributes)
                .build();

	}
	
}
