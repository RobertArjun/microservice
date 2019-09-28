package com.micro.app.ws.api.user.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.micro.app.ws.api.user.entity.UserEntity;
import com.micro.app.ws.api.user.entity.UserRepositery;
import com.micro.app.ws.api.user.shared.UserDto;

@Service
public class UserServiceImpl implements UserService {

	UserRepositery userRepositery;
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserServiceImpl(UserRepositery userRepositery, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepositery = userRepositery;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());
		userDto.setEncryptedPwd(bCryptPasswordEncoder.encode(userDto.getPassword()));

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		UserEntity createdEntity = userRepositery.save(userEntity);

		UserDto returnValue = modelMapper.map(createdEntity, UserDto.class);

		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepositery.findByEmail(username);

		if (userEntity == null)
			throw new UsernameNotFoundException(username);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPwd(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto loadUserByEmail(String username) {
		UserEntity userEntity = userRepositery.findByEmail(username);

		if (userEntity == null)
			throw new UsernameNotFoundException(username);
		
		return new ModelMapper().map(userEntity, UserDto.class);
	}

}
