package com.tobias.managementVehicle.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.tobias.managementVehicle.config.JwtService;
import com.tobias.managementVehicle.dto.AuthRequestDTO;
import com.tobias.managementVehicle.dto.AuthResponseDTO;
import com.tobias.managementVehicle.exception.ResourceNotFoundException;
import com.tobias.managementVehicle.model.User;
import com.tobias.managementVehicle.repository.UserRepository;
import com.tobias.managementVehicle.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private JwtService jwtService;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private AuthenticationManager authenticationManager;

	@Override
	public AuthResponseDTO authenticate(AuthRequestDTO request) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
		
        User user = userRepository.findByEmail(request.getUsername()).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("nome", "Rodolfo");

        String token = jwtService.generateToken(user, extraClaims);
        return new AuthResponseDTO(token);
	}
}
