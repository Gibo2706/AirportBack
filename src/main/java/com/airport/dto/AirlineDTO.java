package com.airport.dto;

import java.util.List;


public record AirlineDTO(
		String email,
		String naziv,
		List<AvionDTO> avioni,
		List<AvionDTO> avioniRented,
		List<PilotDTO> piloti,
		List<LetDTO> letovi
	) {

}
