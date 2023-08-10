package br.com.ribeiro.fernando.showcasebackend.ports.controllers.trophies;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ribeiro.fernando.showcasebackend.domain.entities.requests.trophies.PlatinumTrophyRequest;
import br.com.ribeiro.fernando.showcasebackend.ports.controllers.RequestMappings;

public class PlatinumTrohpiesControllerTests {
	
	private MockMvc mockMvc = MockMvcBuilders
		.standaloneSetup(new PlatinumTrophiesController(null))
		.build();
	
	private ObjectMapper objectMapper = new ObjectMapper()
		.findAndRegisterModules();
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenGameIsNullThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest(null, LocalDate.now(), 1, 10, "https://www.teste.com.br");
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenGameIsEmptyThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest("", LocalDate.now(), 1, 10, "https://www.teste.com.br");
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenAchievementDateIsNullThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest("god of war", null, 1, 10, "https://www.google.com/");
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenPlaytimeIsNullThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest("god of war", LocalDate.now(), null, 10, "https://www.google.com/");
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenPlaytimeIsLessThan1ThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest("god of war", LocalDate.now(), 0, 10, "https://www.google.com/");
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenRatingIsNullThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest("god of war", LocalDate.now(), 1, null, "https://www.google.com/");
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenRatingIsLessThan0ThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest("god of war", LocalDate.now(), 1, -1, "https://www.google.com/");
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenRatingIsAbove10ThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest("god of war", LocalDate.now(), 1, 11, "https://www.google.com/");
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenImageUrlIsNullThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest("god of war", LocalDate.now(), 1, 11, null);
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}
	
	@Test
    @WithMockUser
	void givenPlatinumTrophyRequestWhenImageUrlIsBlankThenReturnBadRequest() throws Exception {
		
		var platinumTrophyRequest = new PlatinumTrophyRequest("god of war", LocalDate.now(), 1, 11, "");
		
		String content = objectMapper.writeValueAsString(platinumTrophyRequest);
		
		var request = MockMvcRequestBuilders
			.post("/".concat(RequestMappings.PLATINUM_TROPHIES))
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		ResultMatcher badRequest = MockMvcResultMatchers
			.status()
			.isBadRequest();
		
		mockMvc
			.perform(request)
			.andExpect(badRequest);
		
	}

}
