package com.uet.k62.web.system.examination.controller;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.UserDetailDTO;
import com.uet.k62.web.system.examination.model.dtos.UserFormRegistrationDTO;
import com.uet.k62.web.system.examination.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "users")
public class UserController {
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/* Resource: /users */

	// Method GET - Returns a list of all active users
	@ApiOperation(value = "Get all users", response = RestBody.class)
	@GetMapping
	public ResponseEntity getAllUsers() {
		RestBody restBody = userService.getAllUsers();
		return  ResponseEntity.ok(restBody);
	}

	// Method POST - Create a new user
	@ApiOperation(value = "Registers a new account", response = RestBody.class)
	@PostMapping
	public ResponseEntity registerAccount(@RequestBody UserFormRegistrationDTO userFormRegistrationDTO) {
//		LOGGER.info(userFormRegistrationDTO.toString());
		RestBody restBody = userService.registerAccount(userFormRegistrationDTO);
		return ResponseEntity.ok(restBody);
	}

	// Method PUT
//	@ApiOperation(value = "Update all users", response = RestBody.class)
//	@PutMapping
//	public ResponseEntity updateAllUsers(){
//		return null;
//	}

	// Method DELETE
//	@ApiOperation(value = "Delete all users", response = RestBody.class)
//	@DeleteMapping
//	public ResponseEntity deleteAllUsers(){
//		return null;
//	}

	/* Resource: /users/id */

	// Method GET - Returns a specific user
	@ApiOperation(value = "Get a user", response = RestBody.class)
	@GetMapping(value = "{id}")
	public ResponseEntity getUser(@PathVariable BigInteger id) {
		RestBody restBody = userService.getUser(id);
		return  ResponseEntity.ok(restBody);
	}

	// Method PUT - Updates a specific user
	@ApiOperation(value = "Updates information user", response = RestBody.class)
	@PutMapping(value = "{id}")
	public ResponseEntity updateAccount(@RequestBody UserDetailDTO userDetailDTO, @PathVariable("id") BigInteger id){
		RestBody restBody = userService.updateInfoUser(userDetailDTO, id);
		return ResponseEntity.ok(restBody);
	}

	// Method DELETE - Deletes a specific user
	@ApiOperation(value = "Deletes a user", response = RestBody.class)
	@DeleteMapping(value = "{id}")
	public ResponseEntity deleteAccount(@PathVariable BigInteger id){
		RestBody restBody = userService.deleteUser(id);
		return ResponseEntity.ok(restBody);
	}
}
