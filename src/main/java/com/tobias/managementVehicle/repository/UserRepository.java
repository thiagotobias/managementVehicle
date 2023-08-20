package com.tobias.managementVehicle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobias.managementVehicle.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);	
}