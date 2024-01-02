package com.airport.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Drzava;

public interface DrzavaRepo extends JpaRepository<Drzava, Integer> {
	public Drzava findByCode(String code);
}
