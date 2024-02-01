package com.airport.dto;

public class UserFlightsReqDTO {
	private String username;

	public UserFlightsReqDTO() {
		super();
	}
	
	public UserFlightsReqDTO(String username) {
		super();
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
