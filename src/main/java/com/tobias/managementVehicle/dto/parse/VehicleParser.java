package com.tobias.managementVehicle.dto.parse;

import com.tobias.managementVehicle.dto.VehicleDTO;
import com.tobias.managementVehicle.dto.NewVehicleDTO;
import com.tobias.managementVehicle.model.Vehicle;

public class VehicleParser {

	public static VehicleDTO toVehicleDTO(Vehicle data) {
		VehicleDTO vehicle = new VehicleDTO();
		vehicle.setId(data.getId());
		vehicle.setMarca(data.getMarca());
		vehicle.setModelo(data.getModelo());
		vehicle.setCor(data.getCor());
		return vehicle;
	}

	public static VehicleDTO optionalToVehicleDTO(Vehicle data) {
		VehicleDTO vehicle = new VehicleDTO();
		vehicle.setId(data.getId());
		vehicle.setMarca(data.getMarca());
		vehicle.setModelo(data.getModelo());
		vehicle.setCor(data.getCor());
		return vehicle;
	}

	public static Vehicle toVehicleEntity(NewVehicleDTO data) {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(data.getId());
		vehicle.setMarca(data.getMarca());
		vehicle.setModelo(data.getModelo());
		vehicle.setCor(data.getCor());
		return vehicle;
	}
}
