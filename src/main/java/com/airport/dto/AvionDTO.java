package com.airport.dto;

public record AvionDTO(
		String tailNumber,
		TipAvionDTO tip,
		String rentedTo
	) {

}
