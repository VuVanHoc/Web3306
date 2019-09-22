package com.uet.k62.web.system.examination.service.impl;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.UserFormRegistrationDTO;
import com.uet.k62.web.system.examination.model.entity.User;
import com.uet.k62.web.system.examination.repository.UserRepository;
import com.uet.k62.web.system.examination.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public RestBody registerAccount(UserFormRegistrationDTO userFormRegistrationDTO) {
//		User user = userRepository.findById()
		return null;
	}
	
	@Override
	public RestBody getAllUser() {
		List<User> users = userRepository.findAllByDeletedIsFalse();
		return RestBody.success(users);
	}
	
	
}
