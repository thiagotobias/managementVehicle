package com.tobias.managementVehicle.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tobias.managementVehicle.dto.VehicleDTO;
import com.tobias.managementVehicle.dto.NewVehicleDTO;
import com.tobias.managementVehicle.service.VehicleService;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll() {
		List<VehicleDTO> vehicleList = vehicleService.findAll();	
        return ResponseEntity.ok(vehicleList);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable(name = "id") Long id) {
		VehicleDTO vehicleList = vehicleService.findById(id);		
        return ResponseEntity.ok(vehicleList);
    }
	
	@PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@Valid @RequestBody NewVehicleDTO vehicle) {
        VehicleDTO createdVehicle = vehicleService.create(vehicle);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdVehicle.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @Valid @RequestBody NewVehicleDTO vehicle) {
    	VehicleDTO monstro = vehicleService.update(vehicle, id);
        return ResponseEntity.ok(monstro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
    	vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
		

}
