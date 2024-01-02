package com.airport.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
