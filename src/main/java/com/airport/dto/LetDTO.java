package com.airport.dto;

import java.util.Date;

public record LetDTO(String fNumber, String departure, String arrival, String tailNumber, String airLine, String datum) {
	public LetDTO(String fNumber, String departure, String arrival) {
		this(fNumber, departure, arrival, null, null, null);
	}
}
