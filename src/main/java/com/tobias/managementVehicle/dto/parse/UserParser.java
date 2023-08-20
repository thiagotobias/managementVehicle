package com.tobias.managementVehicle.dto.parse;

import com.tobias.managementVehicle.dto.NewUserDTO;
import com.tobias.managementVehicle.dto.UserDTO;
import com.tobias.managementVehicle.model.User;

public class UserParser {
	
	public static UserDTO toUserDTO(User data) {
		UserDTO user = new UserDTO();
		user.setId(data.getId());
		user.setName(data.getName());
		user.setCpf(data.getCpf());
		user.setEmail(data.getEmail());
		return user;
	}

	public static UserDTO optionalToUserDTO(User data) {
		UserDTO user = new UserDTO();
		user.setId(data.getId());
		user.setName(data.getName());
		user.setCpf(data.getCpf());
		user.setEmail(data.getEmail());
		return user;
	}

	public static User toUserEntity(NewUserDTO data) {
		User user = new User();
        user.setCpf(data.getCpf());
        user.setPassword(data.getPass());
        user.setName(data.getNome());
        user.setEmail(data.getEmail());
        return user;
	}

}
