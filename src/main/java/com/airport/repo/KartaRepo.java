package com.airport.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Karta;
import model.Korisnik;

public interface KartaRepo extends JpaRepository<Karta, Integer> {

	@Query("Select k from Karta k where k.korisnikBean = :k")
	public List<Karta> findAllByKorisnik(Korisnik k);
}
