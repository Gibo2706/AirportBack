package com.airport.dto;

import java.util.List;

public class ConfirmBookDTO {
	private String fNumber;
	private List<SedisteDTO> seats;
	private String username;
	private String password;

	public String getfNumber() {
		return fNumber;
	}

	public void setfNumber(String fNumber) {
		this.fNumber = fNumber;
	}

	public List<SedisteDTO> getSeats() {
		return seats;
	}

	public void setSeats(List<SedisteDTO> seats) {
		this.seats = seats;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
