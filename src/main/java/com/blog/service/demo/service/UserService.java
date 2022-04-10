package com.blog.service.demo.service;

import java.util.List;

import com.blog.service.dto.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO dto);

	UserDTO findById(Long id);

	List<UserDTO> findAllUsers();

	void deleteUser(Long id);

	public UserDTO updateUser(UserDTO dto,Long id);
}
