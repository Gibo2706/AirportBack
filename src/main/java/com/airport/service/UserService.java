package com.airport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.airport.repo.DrzavaRepo;
import com.airport.repo.KartaRepo;
import com.airport.repo.KorisnikRepo;
import com.airport.repo.RoleRepo;

import model.Drzava;
import model.Karta;
import model.Korisnik;

@Service
public class UserService {

	@Autowired
	KorisnikRepo kor;
	
	@Autowired
	KartaRepo kr;
	
	@Autowired 
	RoleRepo rr;
	
	@Autowired
	DrzavaRepo dr;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	public Korisnik getKorisnik(int id) {
		return kor.findById(id).get();
	}
	
	public Korisnik findByUsername(String name) {
		return kor.findByUsername(name).get();
	}
	
	public List<Karta> getFlights(Korisnik korisnik){
		return kr.findAllByKorisnik(korisnik);
	}
	
	public List<Drzava> getDrzave(){
		return dr.findAll();
	}
 	
	public Drzava findByCode(String code) {
		return dr.findByCode(code);
	}
	
	public void save(Korisnik k) {
		k.setPassword(bCryptPasswordEncoder.encode(k.getPassword()));
		k.setRole(rr.findById(3).get());
		kor.save(k);
	}
	
	public void update(Korisnik k) {
		kor.save(k);
	}
	
	public void delete(Korisnik k) {
		kor.delete(k);
	}
}
