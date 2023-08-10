package br.com.ribeiro.fernando.showcasebackend.domain.entities.trophies;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.ribeiro.fernando.showcasebackend.domain.entities.requests.trophies.PlatinumTrophyRequest;

@Document("platinum-trophies")
public class PlatinumTrophy {
	
	@Id
	private ObjectId id;
	private String game;
	private LocalDate achievementDate;
	private Integer hoursPlayed;
	private Integer rating;
	private String imageUrl;
	
	public PlatinumTrophy() {}
	
	public PlatinumTrophy(PlatinumTrophyRequest platinumTrophyRequest) {
		this.game = platinumTrophyRequest.getGame();
		this.achievementDate = platinumTrophyRequest.getAchievementDate();
		this.hoursPlayed = platinumTrophyRequest.getHoursPlayed();
		this.rating = platinumTrophyRequest.getRating();
		this.imageUrl = platinumTrophyRequest.getImageUrl();
	}

	public ObjectId getId() {
		return id;
	}
	
	public String getGame() {
		return game;
	}
	
	public LocalDate getAchievementDate() {
		return achievementDate;
	}
	
	public Integer getHoursPlayed() {
		return hoursPlayed;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
}
