package com.tobias.managementVehicle.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tobias.managementVehicle.dto.NewUserDTO;
import com.tobias.managementVehicle.dto.UserDTO;
import com.tobias.managementVehicle.dto.parse.UserParser;
import com.tobias.managementVehicle.exception.ResourceNotFoundException;
import com.tobias.managementVehicle.model.User;
import com.tobias.managementVehicle.repository.UserRepository;
import com.tobias.managementVehicle.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> userList = userRepository.findAll().stream()
                .map(UserParser:: toUserDTO)
                .collect(Collectors.toList()); ;
		
		return userList;
	}

	@Override
	public UserDTO findById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);

		return UserParser.optionalToUserDTO(
				userOptional.orElseThrow(
						() -> new ResourceNotFoundException("Não encontrado nenhum usuário com o ID " + id))
				);
	}

	@Override
	public UserDTO create(NewUserDTO user) {
		user.setPass(encoder.encode(user.getPass()));
        return UserParser
                .toUserDTO(userRepository.save(UserParser.toUserEntity(user)));
	}

	public UserDTO update(NewUserDTO newUser, Long id) {
		findById(id);
        
		User userEntity = UserParser.toUserEntity(newUser);
        userEntity.setId(id);

        return UserParser
                .toUserDTO(userRepository.save(userEntity));
    }

	@Override
	public void delete(Long id) {
		UserDTO userDTO = findById(id);
	    userRepository.deleteById(userDTO.getId());	
	}

}
