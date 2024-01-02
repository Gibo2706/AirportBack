package com.airport.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Aerodrom;

public interface AerodromRepo extends JpaRepository<Aerodrom, Integer> {

}
