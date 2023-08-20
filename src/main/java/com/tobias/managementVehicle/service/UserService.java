package com.tobias.managementVehicle.service;

import java.util.List;

import com.tobias.managementVehicle.dto.NewUserDTO;
import com.tobias.managementVehicle.dto.UserDTO;

import jakarta.validation.Valid;

public interface UserService {

	List<UserDTO> findAll();
	UserDTO findById(Long id);
	UserDTO create(@Valid NewUserDTO user);
	UserDTO update(NewUserDTO usuario, Long id);
	void delete(Long id);

}
