package com.tobias.managementVehicle.service;

import com.tobias.managementVehicle.dto.AuthRequestDTO;
import com.tobias.managementVehicle.dto.AuthResponseDTO;

public interface AuthService {
	AuthResponseDTO authenticate(AuthRequestDTO request);
}
