package com.airport.dto;


public class LoginResponseDTO {
	String token;
	Object user;

	public LoginResponseDTO() {
	}

	public LoginResponseDTO(String token, Object user ) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return this.token;
	}

	public Object getUser() {
		return this.user;
	}

}
