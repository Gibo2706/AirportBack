package com.airport.response.body;

import java.util.List;

public class SeatSelectionRequest {
	private List<SedisteJson> selectedSeats;
	private String flightNumber;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public List<SedisteJson> getSelectedSeats() {
		return selectedSeats;
	}

	public void setSelectedSeats(List<SedisteJson> selectedSeats) {
		this.selectedSeats = selectedSeats;
	}

}
