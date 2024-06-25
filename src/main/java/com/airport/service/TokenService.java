package com.airport.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.repo.KljucRepo;

import model.Kljuc;

@Service
public class TokenService {
	
	@Autowired
	KljucRepo kr;
	
	public String generateTokenForUser(int id) {
		Kljuc k = new Kljuc();
		k.setIdKorisnik(id);
		k.setKey(UUID.randomUUID().toString());
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MINUTE, 30);
		//k.setValid(new SimpleDateFormat("dd/MM/yyy HH:mm:ss").format(c.getTime()));
		k.setValid(c.getTime());
		k = kr.save(k);
		return k.getKey();
	}
	
	public boolean isValidToken(int id, String token) {
		Kljuc k = kr.findByIdKorisnikAndKljuc(id, token);
		if(k.getValid().before(new Date())) return false;
		return true;
	}
}
