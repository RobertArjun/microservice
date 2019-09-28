package com.micro.app.ws.api.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.micro.app.ws.api.user.shared.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto userDto);

	UserDto loadUserByEmail(String userName);
}
