package com.airport.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import model.Korisnik;

public class CustOAuth2User implements OAuth2User {
	private Korisnik user;
	private UserDetails ud;
	private OAuth2User o2;
	
	public CustOAuth2User(Korisnik k, OAuth2User o2, UserDetails ud) {
		this.o2 = o2;
		this.setUser(k);
		this.setUd(ud);
	}
	


	@Override
	public Map<String, Object> getAttributes() {
		
		return o2.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return ud.getAuthorities();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}



	public Korisnik getUser() {
		return user;
	}



	public void setUser(Korisnik user) {
		this.user = user;
	}



	public UserDetails getUd() {
		return ud;
	}



	public void setUd(UserDetails ud) {
		this.ud = ud;
	}

}
