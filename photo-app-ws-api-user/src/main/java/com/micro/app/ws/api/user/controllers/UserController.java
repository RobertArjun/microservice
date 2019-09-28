package com.micro.app.ws.api.user.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.app.ws.api.user.service.UserService;
import com.micro.app.ws.api.user.shared.UserDto;
import com.micro.app.ws.api.user.ui.models.LoginRequest;
import com.micro.app.ws.api.user.ui.models.UserRequest;
import com.micro.app.ws.api.user.ui.models.UserResponse;

@RestController
@RequestMapping(path = "user")
public class UserController {
	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@GetMapping(path = "/{message}")
	public String message(@PathVariable String message) {
		return "Working " + message + " Port " + env.getProperty("local.server.port");

	}

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
		UserResponse returnValue = modelMapper.map(createdUser, UserResponse.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

	}
	@PostMapping(path = "/login")
	private ResponseEntity login(@RequestBody LoginRequest loginRequest) {
		System.out.println("Test");
		return null;
		
	}
}
