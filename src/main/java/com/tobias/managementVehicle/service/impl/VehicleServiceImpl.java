package com.tobias.managementVehicle.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobias.managementVehicle.dto.VehicleDTO;
import com.tobias.managementVehicle.dto.NewVehicleDTO;
import com.tobias.managementVehicle.dto.parse.VehicleParser;
import com.tobias.managementVehicle.exception.ResourceNotFoundException;
import com.tobias.managementVehicle.model.Vehicle;
import com.tobias.managementVehicle.repository.VehicleRepository;
import com.tobias.managementVehicle.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public List<VehicleDTO> findAll() {

		List<VehicleDTO> vehicleList = vehicleRepository.findAll().stream().map(VehicleParser::toVehicleDTO)
				.collect(Collectors.toList());
		;

		return vehicleList;
	}

	@Override
	public VehicleDTO findById(Long id) {
		Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

		return VehicleParser.optionalToVehicleDTO(vehicleOptional
				.orElseThrow(() -> new ResourceNotFoundException("Não encontrado nenhum veículo com o ID " + id)));
	}

	@Override
	public VehicleDTO create(NewVehicleDTO vehicle) {
		return VehicleParser
                .toVehicleDTO(vehicleRepository.save(VehicleParser.toVehicleEntity(vehicle)));
	}

	@Override
	public VehicleDTO update(NewVehicleDTO vehicle, Long id) {
		findById(id);

		Vehicle vehicleEntity = VehicleParser.toVehicleEntity(vehicle);
		vehicleEntity.setId(id);

		return VehicleParser.toVehicleDTO(vehicleRepository.save(vehicleEntity));
	}

	@Override
	public void delete(Long id) {
		VehicleDTO vehicle = findById(id);
		vehicleRepository.deleteById(vehicle.getId());
	}
}
