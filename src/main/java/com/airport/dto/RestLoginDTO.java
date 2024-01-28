package com.airport.dto;

public class RestLoginDTO {
	private String username;
	private String password;
	
	public RestLoginDTO() {
	}

	public RestLoginDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
}
