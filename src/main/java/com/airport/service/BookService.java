package com.airport.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.repo.AerodromRepo;
import com.airport.repo.BookRepo;
import com.airport.repo.KartaRepo;
import com.airport.repo.KorisnikRepo;
import com.airport.repo.SedisteRepo;

import model.Aerodrom;
import model.Karta;
import model.Let;
import model.Sediste;

@Service
public class BookService {
	@Autowired
	AerodromRepo ar;
	
	@Autowired
	SedisteRepo sr;
	
	@Autowired
	BookRepo br;
	
	@Autowired
	KartaRepo kr;
	
	@Autowired
	KorisnikRepo kor;
	
	
	
	public List<Aerodrom> getAllAerodrom(){
		return ar.findAll();
	}
	
	
	public List<Let> getFlights(Date date, Date dateE, int idDep, int idArr) throws ParseException{
		return br.getFlights(idDep, idArr, date, dateE);
	}
	
	public List<Sediste> getSeatsForFlight(String fNumber){
		return br.getSeatsForFlight(fNumber);
	}
	
	public Sediste findById(int row, int column) {
		return sr.findByRowAndColumn(row, column);
	}
	
	public Karta makeReservation(String fNumber, Sediste sediste, String owner) {
		Karta k = new Karta();
		k.setLet(br.findById(fNumber).get());
		k.setSediste(sediste);
		k.setKorisnikBean(kor.findByUsername(owner).get());
		k = kr.save(k);
		return k;
	}
}
