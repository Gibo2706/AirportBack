package com.airport.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Sediste;

public interface SedisteRepo extends JpaRepository<Sediste, Integer> {
	
	@Query("Select s from Sediste s where s.id.row = :row and s.id.column = :column")
	public Sediste findByRowAndColumn(int row, int column);
}
