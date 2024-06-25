package com.airport.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Kljuc;

public interface KljucRepo extends JpaRepository<Kljuc, Integer> {

	Kljuc findByIdKorisnikAndKljuc(int id, String kljuc);
}
