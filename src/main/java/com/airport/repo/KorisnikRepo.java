package com.airport.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;

public interface KorisnikRepo extends JpaRepository<Korisnik, Integer> {
	Optional<Korisnik> findByUsername(String username);
}
