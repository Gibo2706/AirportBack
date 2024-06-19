package com.airport.service;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.dto.KartaDTO;
import com.airport.dto.SedisteDTO;
import com.airport.repo.AerodromRepo;
import com.airport.repo.BookRepo;
import com.airport.repo.KartaRepo;
import com.airport.repo.KorisnikRepo;
import com.airport.repo.SedisteRepo;

import model.Aerodrom;
import model.Karta;
import model.Let;
import model.Sediste;
import model.SedistePK;

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
	
	public Aerodrom getAerodrom(String naziv) {
		return ar.findByNaziv(naziv);
	}
	
	public List<Let> getFlights(Date date, Date dateE, int idDep, int idArr) throws ParseException{
		return br.getFlights(idDep, idArr, date, dateE);
	}
	
	public List<Sediste> getSeatsForFlight(String fNumber){
		return br.getSeatsForFlight(fNumber);
	}
	
	public List<SedisteDTO> getSeatsForFlight(String fNumber, boolean isRest){
		System.out.println(fNumber);
		List<Sediste> sedista = br.getSeatsForFlight(fNumber);
		List<SedisteDTO> sedistaDTO = new LinkedList<SedisteDTO>();
		for (Sediste s : sedista) {
			sedistaDTO.add(new SedisteDTO(s.getId().getRedovi(), s.getId().getKolone()));
		}
		return sedistaDTO;
	}
	
	public Sediste findById(int row, int column) {
		return sr.findByRowAndColumn(row, column);
	}
	
	public List<Let> getAllFlights(){
		return br.findAll();
	}
	
	public List<KartaDTO> confirmFlightBook(String fNumber, List<SedisteDTO> sedisteDTO, String owner){
		List<Karta> karte = new LinkedList<Karta>();
		for (SedisteDTO s : sedisteDTO) {
			System.out.println(s.getRow() + " " + s.getColumn());
			Sediste sediste = sr.findByRowAndColumn(s.getRow(), s.getColumn());
			System.out.println(sediste.getId().getRedovi() + " " + sediste.getId().getKolone());
			karte.add(makeReservation(fNumber, sediste, owner));
		}
		List<KartaDTO> karteDTO = new LinkedList<KartaDTO>();
		for (Karta k : karte) {
			karteDTO.add(new KartaDTO(k.getId(), k.getKorisnikBean(), k.getLet(),
					new SedisteDTO(k.getSediste().getId().getRedovi(), k.getSediste().getId().getKolone())));
		}
		return karteDTO;
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
