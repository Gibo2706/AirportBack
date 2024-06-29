package com.airport.dto;

public record AvionDTO(
		String tailNumber,
		TipAvionDTO tip,
		String rentedTo,
		String avioKompanija
	) {
		public AvionDTO(String tailNumber, TipAvionDTO tip, String rentedTo) {
			this(tailNumber, tip, rentedTo, null);
		}
}
