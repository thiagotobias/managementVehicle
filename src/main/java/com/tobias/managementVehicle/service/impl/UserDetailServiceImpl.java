package com.tobias.managementVehicle.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tobias.managementVehicle.dto.UserDetailsInfo;
import com.tobias.managementVehicle.exception.ResourceNotFoundException;
import com.tobias.managementVehicle.model.User;
import com.tobias.managementVehicle.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = repository.findByEmail(username);
        return new UserDetailsInfo(optionalUser.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado")));
    }
}
