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

import com.tobias.managementVehicle.dto.NewUserDTO;
import com.tobias.managementVehicle.dto.UserDTO;
import com.tobias.managementVehicle.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> asdf = userService.findAll();
        return ResponseEntity.ok(asdf);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
    	UserDTO userList = userService.findById(id);		
        return ResponseEntity.ok(userList);
    }
   
    @PostMapping
    public ResponseEntity<UserDTO> salvar(@RequestBody @Valid NewUserDTO user) {
        UserDTO userSave = userService.create(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userSave.getId())
                .toUri();

        return ResponseEntity.created(uri).body(userSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> atualizar(@RequestBody NewUserDTO usuario,
                                             @PathVariable Long id) {
    	UserDTO user = userService.update(usuario, id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
