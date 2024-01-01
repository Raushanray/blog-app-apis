package com.codewithraushanblog.services.impl;

import com.codewithraushanblog.config.AppConstants;
import com.codewithraushanblog.entities.Role;
import com.codewithraushanblog.entities.User;
import com.codewithraushanblog.exceptions.ResourceNotFoundException;
import com.codewithraushanblog.payloads.UserDto;
import com.codewithraushanblog.repositories.RoleRepo;
import com.codewithraushanblog.repositories.UserRepo;
import com.codewithraushanblog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {


		User user = this.dtoToUser(userDto);
		User saveduser = this.userRepo.save(user);

		return this.UserToDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));


		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updateUser = this.userRepo.save(user);
		UserDto userDto1 = this.UserToDto(updateUser);

		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		return this.UserToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> usersDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());

		return usersDtos;
	}

	@Override
	public void deletUser(Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		this.userRepo.delete(user);

	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelmapper.map(userDto, User.class);

//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;


	}

	public UserDto UserToDto(User user) {
		UserDto userDto = this.modelmapper.map(user, UserDto.class);

//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelmapper.map(userDto, User.class);

//		encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

//		roles

		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);

		return this.modelmapper.map(newUser, UserDto.class);
	}
}






