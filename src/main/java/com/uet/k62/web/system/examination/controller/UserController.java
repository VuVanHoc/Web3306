package com.uet.k62.web.system.examination.controller;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.UserFormRegistrationDTO;
import com.uet.k62.web.system.examination.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Register a new account", response = RestBody.class)
	@PostMapping(value = "createAccount")
	public ResponseEntity registerAccount(@RequestBody UserFormRegistrationDTO userFormRegistrationDTO) {
//		LOGGER.info(userFormRegistrationDTO.toString());
		RestBody restBody = userService.registerAccount(userFormRegistrationDTO);
		return ResponseEntity.ok(restBody);
	}
	
	@ApiOperation(value = "Get all users", response = RestBody.class)
	@GetMapping(value = "getAllUsers")
	public ResponseEntity getAllUser() {
		RestBody restBody = userService.getAllUser();
		return  ResponseEntity.ok(restBody);
	}
}
