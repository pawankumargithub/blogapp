package com.blog.service.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.service.demo.service.UserService;
import com.blog.service.demo.serviceimpl.UserserviceImpl;
import com.blog.service.dto.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService service;
	
	
	@PostMapping("/create")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto){
		LOGGER.info("inside user controller:"+dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(dto));
	}
	
	@GetMapping("/find-all")
	public ResponseEntity<List<UserDTO>> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAllUsers());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		service.deleteUser(id);
		return new ResponseEntity<>("deleted :"+id,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO dto,@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.updateUser(dto,id));
	}
}
