package br.com.ribeiro.fernando.showcasebackend.domain.entities.requests.trophies;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlatinumTrophyRequest {
	
	@NotNull(message = "game must not be null")
	@NotBlank(message = "game must not be blank")
	private String game;
	
	@NotNull(message = "achievementDate must not be null")
	private LocalDate achievementDate;
	
	@NotNull
	@Min(value = 1, message = "hoursPlayed must be at least 1")
	private Integer hoursPlayed;
	
	@NotNull(message = "rating must not be null")
	@Min(value = 0, message = "rating must be at least 0")
    @Max(value = 10, message = "rating must be at most 10")
	private Integer rating;
	
	@NotNull(message = "imageUrl must not be null")
	@NotBlank(message = "imageUrl must not be blank")
	private String imageUrl;
	
	public PlatinumTrophyRequest() {};

	public PlatinumTrophyRequest(
			String game,
			LocalDate achievementDate,
			Integer hoursPlayed,
			Integer rating,
			String imageUrl
			) 
	{
		this.game = game;
		this.achievementDate = achievementDate;
		this.hoursPlayed = hoursPlayed;
		this.rating = rating;
		this.imageUrl = imageUrl;
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
