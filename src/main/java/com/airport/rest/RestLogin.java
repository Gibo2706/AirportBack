package com.airport.rest;

public class RestLogin {
	private String username;
	private String password;
	
	public RestLogin() {
	}

	public RestLogin(String username, String password) {
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
