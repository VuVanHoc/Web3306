package com.uet.k62.web.system.examination.controller;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.UserDetailDTO;
import com.uet.k62.web.system.examination.model.dtos.UserFormRegistrationDTO;
import com.uet.k62.web.system.examination.model.entity.User;
import com.uet.k62.web.system.examination.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Register a new account", response = RestBody.class)
	@PostMapping
	public ResponseEntity registerAccount(@RequestBody UserFormRegistrationDTO userFormRegistrationDTO) {
		RestBody restBody = userService.registerAccount(userFormRegistrationDTO);
		return ResponseEntity.ok(restBody);
	}
	
	@ApiOperation(value = "Get all users", response = RestBody.class)
	@GetMapping
	public ResponseEntity getAllUser() {
		LOGGER.info("Hello");
		RestBody restBody = userService.getAllUser();
		return  ResponseEntity.ok(restBody);
	}
	
	@ApiOperation(value = "Update info user", response =  RestBody.class)
	@PutMapping( value = "{id}")
	public ResponseEntity updateInfoUser(@RequestBody UserDetailDTO userDetailDTO) {
		return null;
	}
	
	
	@DeleteMapping(value = "{id}")
	public ResponseBody deleteUser() {
		return null;
	}
}
