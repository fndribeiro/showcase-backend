package br.com.ribeiro.fernando.showcasebackend.domain.entities.responses.trophies;

import java.time.LocalDate;

import br.com.ribeiro.fernando.showcasebackend.domain.entities.trophies.PlatinumTrophy;

public record PlatinumTrophyResponse(String game, LocalDate achievementDate, Integer hoursPlayed, Integer rating, String imageUrl) {
	
	public PlatinumTrophyResponse(PlatinumTrophy platinumTrophy) {
		this(
				platinumTrophy.getGame(), 
				platinumTrophy.getAchievementDate(), 
				platinumTrophy.getHoursPlayed(), 
				platinumTrophy.getRating(), 
				platinumTrophy.getImageUrl()
			);
	}
	
}
