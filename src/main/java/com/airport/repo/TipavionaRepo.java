package com.airport.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Tipaviona;

public interface TipavionaRepo extends JpaRepository<Tipaviona, Integer> {
	
	Tipaviona findByNaziv(String naziv);
}
