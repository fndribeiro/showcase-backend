package br.com.ribeiro.fernando.showcasebackend.ports.controllers.trophies;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ribeiro.fernando.showcasebackend.application.services.trophies.PlatinumTrophiesService;
import br.com.ribeiro.fernando.showcasebackend.domain.entities.requests.trophies.PlatinumTrophySortingParameters;
import br.com.ribeiro.fernando.showcasebackend.domain.entities.requests.trophies.PlatinumTrophyRequest;
import br.com.ribeiro.fernando.showcasebackend.domain.entities.responses.trophies.PlatinumTrophyResponse;
import br.com.ribeiro.fernando.showcasebackend.domain.entities.trophies.PlatinumTrophy;
import br.com.ribeiro.fernando.showcasebackend.ports.controllers.RequestMappings;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(RequestMappings.PLATINUM_TROPHIES)
@Tag(name = "Platinum Trophies")
public class PlatinumTrophiesController {
	
	private final PlatinumTrophiesService platinumTrophiesService;
	
	public PlatinumTrophiesController(PlatinumTrophiesService platinumTrophiesService) {
		this.platinumTrophiesService = platinumTrophiesService;
	}

	@PostMapping
	@Operation(summary = "Save new platinum trophy.")
	public ResponseEntity<PlatinumTrophy> save(@RequestBody @Valid PlatinumTrophyRequest platinumTrophyRequest) {
		
		platinumTrophiesService.save(platinumTrophyRequest);
		
		URI location = URI.create(RequestMappings.PLATINUM_TROPHIES);
		
		return ResponseEntity
				.created(location)
				.build();
		
	}
	
	@GetMapping
	@Operation(summary = "Find all platinum trophies. Use parameter to define sorting.")
	public ResponseEntity<List<PlatinumTrophyResponse>> findAll(@RequestParam(defaultValue = "achievementDate") String sortBy, @RequestParam(defaultValue = "asc") String direction) {
		
		String parameter = PlatinumTrophySortingParameters
			.findParameter(sortBy)
			.getValue();
		
		List<PlatinumTrophyResponse> platinumTrophies = platinumTrophiesService.findAll(parameter, direction);
		
		return ResponseEntity.ok(platinumTrophies);
		
	}

}
