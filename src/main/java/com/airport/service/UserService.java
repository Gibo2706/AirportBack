package com.airport.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.airport.dto.KartaDTO;
import com.airport.dto.KorisnikDTO;
import com.airport.dto.SedisteDTO;
import com.airport.repo.DrzavaRepo;
import com.airport.repo.KartaRepo;
import com.airport.repo.KljucRepo;
import com.airport.repo.KorisnikRepo;
import com.airport.repo.RoleRepo;

import model.Drzava;
import model.Karta;
import model.Kljuc;
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
	KljucRepo kljr;
	
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
	
	public List<KartaDTO> getFlights(Korisnik korisnik, boolean isRest) {
		List<Karta> karte = kr.findAllByKorisnik(korisnik);
		List<KartaDTO> karteDTO = new ArrayList<KartaDTO>();
		for (Karta k : karte) {
			karteDTO.add(new KartaDTO(k.getId(), k.getKorisnikBean(), k.getLet(), new SedisteDTO(k.getSediste().getId().getRedovi(), k.getSediste().getId().getKolone())));
		}
		return karteDTO;
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
	
	public void changePassword(Korisnik k, String password) {
		k.setPassword(bCryptPasswordEncoder.encode(password));
		System.out.println(k.getPassword());
		kor.save(k);
	}
	
	public void changeEmail(Korisnik k, String email) {
		k.setEmail(email);
		kor.save(k);
	}
	
	public void changeUsername(Korisnik k, String username) {
		k.setUsername(username);
		kor.save(k);
	}
	
	public void changePhone(Korisnik k, String phone) {
		k.setPhone(phone);
		kor.save(k);
	}

	public boolean checkUsername(String username) {
		if (kor.findByUsername(username).isPresent())
			return false;
		return true;
	}

	public boolean checkPassword(String password) {
		if (password.length() >= 8)
			return true;
		return false;
	}

	public boolean checkRole(String role) {
		if (role.equals("USER") || role.equals("ADMIN") || role.equals("AVIOCOMPANY"))
			return true;
		return false;
	}
	
	public int addUser(String username, String password, String email, String role) {
		Korisnik k = new Korisnik();
		k.setUsername(username);
		k.setPassword(bCryptPasswordEncoder.encode(password));
		k.setRole(rr.findByName(role));
		k.setDrzavaBean(dr.findByCode("SRB"));
		k.setPhone("0000000000");
		k.setEmail(email);
		k.setName("changeName");
		k.setSurname("changeSurname");
		
		k = kor.save(k);
		return k.getId();
	}

	public void changeName(Korisnik byUsername, String name) {
		byUsername.setName(name);
        kor.save(byUsername);		
	}

	public void changeSurname(Korisnik byUsername, String surname) {
		byUsername.setSurname(surname);
		kor.save(byUsername);
		
	}

	public void changeProfilePicture(Korisnik byUsername, MultipartFile profilePicture) throws IOException {
		byUsername.setProfPicture(profilePicture.getBytes());
		kor.saveAndFlush(byUsername);
	}

	public List<Drzava> getCountries() {
		return dr.findAll();
	}
	
	public void updateUser(KorisnikDTO dtoK) {
		Korisnik k = findByUsername(dtoK.username());
		k.setEmail(dtoK.email());
		k.setName(dtoK.name());
		k.setSurname(dtoK.surname());
		k.setPhone(dtoK.phone());
		k.setUsername(dtoK.username());
		kor.save(k);
	}
}
