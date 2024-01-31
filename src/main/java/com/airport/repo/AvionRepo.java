package com.airport.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Avion;

public interface AvionRepo extends JpaRepository<Avion, String> {
	
	@Query("select a from Avion a where a.aviokompanija.id = :id")
	List<Avion> findByAviokompanija(int id);
	
	@Query("select a from Avion a where a.aviokompanija2.id = :id")
	List<Avion> findByAviokompanija2(int id);

}
