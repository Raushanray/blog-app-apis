package com.codewithraushanblog.services;

import com.codewithraushanblog.payloads.UserDto;

import java.util.List;

public interface UserService {


	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deletUser(Integer userId);
}
