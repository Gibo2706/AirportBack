package com.airport.dto;

public record PilotDTO(
		String ime,
		String prezime,
		String avioKompanija
	) {
		public PilotDTO(String ime, String prezime) {
			this(ime, prezime, null);
		}
}
