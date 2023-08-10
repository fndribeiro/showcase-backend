package br.com.ribeiro.fernando.showcasebackend.application.services.trophies;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.ribeiro.fernando.showcasebackend.domain.entities.requests.trophies.PlatinumTrophyRequest;
import br.com.ribeiro.fernando.showcasebackend.domain.entities.responses.trophies.PlatinumTrophyResponse;
import br.com.ribeiro.fernando.showcasebackend.domain.entities.trophies.PlatinumTrophy;
import br.com.ribeiro.fernando.showcasebackend.ports.repositories.trophies.PlatinumTrophyRepository;

@Service
public class PlatinumTrophiesService {
	
	private final PlatinumTrophyRepository repository;
	
	public PlatinumTrophiesService(PlatinumTrophyRepository repository) {
		this.repository = repository;
	}

	public PlatinumTrophy save(PlatinumTrophyRequest platinumTrophyRequest) {
		
		var platinumTrophy = new PlatinumTrophy(platinumTrophyRequest);
		
		return repository.save(platinumTrophy);
		
	}
	
	public List<PlatinumTrophyResponse> findAll(String property, String sortingDirection) {
		
		Direction direction = Sort
				.Direction
				.fromString(sortingDirection);
		
		return repository
				.findAll(Sort.by(direction, property))
				.stream()
				.map(PlatinumTrophyResponse::new)
				.toList();
	}

}
