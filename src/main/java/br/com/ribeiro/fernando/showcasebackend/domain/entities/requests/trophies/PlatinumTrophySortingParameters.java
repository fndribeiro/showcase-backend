package br.com.ribeiro.fernando.showcasebackend.domain.entities.requests.trophies;

import java.util.Arrays;

public enum PlatinumTrophySortingParameters {

	GAME ("game"),
    ACHIEVEMENT_DATE ("achievementDate"),
    HOURS_PLAYED ("hoursPlayed"),
    RATING ("rating");
	
	private final String value;

	private PlatinumTrophySortingParameters(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static PlatinumTrophySortingParameters findParameter(String value) {
		return Arrays
			.asList(PlatinumTrophySortingParameters.values())
			.stream()
			.filter(param -> param.value.equals(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Invalid param " + value));
	}

}
