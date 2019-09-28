package com.micro.app.ws.api.user.entity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepositery extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
}
