package com.blog.service.demo.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.blog.service.demo.service.UserService;
import com.blog.service.dto.UserDTO;
import com.blog.service.entity.User;
import com.blog.service.exception.UserNotFoundException;
import com.blog.service.repository.UserRepository;

@Service
public class UserserviceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserserviceImpl.class);

	private static final String USER_WITH_ID = "user with id";
	private static final String NOT_EXISTED = "not existed";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDTO createUser(UserDTO dto) {
		LOGGER.info("inside create api");
		if (ObjectUtils.isEmpty(dto)) {
			LOGGER.info("exception occured while creating user:"+ dto);
			throw new UserNotFoundException("user cant be empty");
			
		}
		User user = toEntity(dto);
		userRepository.save(user);
		LOGGER.info("user created successfully:"+ dto);
		return toDto(user);
	}

	@Override
	public UserDTO findById(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(USER_WITH_ID + " " + id + " " + " not existed"));
		return toDto(user);
	}

	@Override
	public List<UserDTO> findAllUsers() {

		List<UserDTO> userDTOs = userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(userDTOs)) {
			throw new UserNotFoundException("user list is empty");
		}
		return userDTOs;
	}

	@Override
	public void deleteUser(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(USER_WITH_ID + " " + id + " " + NOT_EXISTED));
		userRepository.delete(user);
	}

	@Override
	public UserDTO updateUser(UserDTO dto, Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(USER_WITH_ID + " " + id + " " + NOT_EXISTED));

		user.setDescription(dto.getDescription());
		user.setFirstName(dto.getFirstName());
		user.setId(dto.getId());
		user.setMiddleName(dto.getMiddleName());
		user.setPassword(dto.getPassword());
		userRepository.save(user);
		return toDto(user);
	}

	private User toEntity(UserDTO dto) {
		return mapper.map(dto, User.class);
	}

	private UserDTO toDto(User user) {
		return mapper.map(user, UserDTO.class);
	}
}
