package com.airport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.airport.repo.KorisnikRepo;
import com.airport.repo.RoleRepo;

import model.Korisnik;

@Service
public class AuthService implements UserDetailsService{
	@Autowired
	KorisnikRepo kr;
	
	@Autowired
	RoleRepo rr;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik k = kr.findByUsername(username).get();
		UserDetails ud = new AuthUser(k);
		return ud;
	}
	
	public Korisnik loadUserByUsernameOAuth(String username) throws UsernameNotFoundException {
		return kr.findByUsername(username).get();
	}
	
	
	
	public boolean saveUser(OAuth2User us) {
		boolean saved = false;
		String username = us.getAttribute("login");
		Korisnik k = new Korisnik();
		k.setUsername(username);
		String name[] = ((String) us.getAttribute("name")).split(" ");
		k.setName(name[0]);
		k.setSurname(name[1]);
		k.setRole(rr.findById(3).get());
		k = kr.save(k);
		if(k != null)
			saved = true;
		
		return saved;
	}
	
}
