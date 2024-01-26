package com.airport.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Pilot;

public interface PilotRepo extends JpaRepository<Pilot, Integer> {

	@Query("select p from Pilot p where p.aviokompanija.naziv = :airline")
	List<Pilot> findByAviokompanija(String airline);

}
