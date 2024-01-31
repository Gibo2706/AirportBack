package com.airport.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Let;
import model.Sediste;

public interface BookRepo extends JpaRepository<Let, String>{
	@Query("Select l from Let l where l.aerodrom1.id=:idDep and l.aerodrom2.id=:idArr and l.datum <= :dateE and l.datum >= :date")
	List<Let> getFlights(int idDep, int idArr, Date date, Date dateE);
	
	@Query("Select s from Sediste s where s not in (select s1 from Sediste s1, Karta k where :fNumber=k.let.fNumber and k.sediste.id.redovi = s1.id.redovi and k.sediste.id.kolone = s1.id.kolone)")
	List<Sediste> getSeatsForFlight(String fNumber);

	@Query("Select l from Let l where l.aviokompanija.id = :id")
	List<Let> findByAvioKompanija(int id);
}
	