package com.airport.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Aviokompanija;

public interface AvioRepo extends JpaRepository<Aviokompanija, Integer> {

	Aviokompanija findByEmail(String email);

}
