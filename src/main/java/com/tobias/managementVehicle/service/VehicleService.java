package com.tobias.managementVehicle.service;

import java.util.List;

import com.tobias.managementVehicle.dto.VehicleDTO;
import com.tobias.managementVehicle.dto.NewVehicleDTO;

public interface VehicleService {
	public List<VehicleDTO> findAll();
	public VehicleDTO findById(Long id);
	public VehicleDTO create(NewVehicleDTO vehicle);
	public VehicleDTO update(NewVehicleDTO vehicle, Long id);
	public void delete(Long id);
}
