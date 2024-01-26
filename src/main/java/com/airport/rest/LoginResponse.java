package com.airport.rest;


public class LoginResponse {
	String token;
	Object user;

	public LoginResponse() {
	}

	public LoginResponse(String token, Object user ) {
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
