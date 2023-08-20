package com.tobias.managementVehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobias.managementVehicle.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	
}