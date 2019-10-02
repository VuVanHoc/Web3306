package com.uet.k62.web.system.examination.service;

import com.uet.k62.web.system.examination.model.RestBody;
import com.uet.k62.web.system.examination.model.dtos.UserDetailDTO;
import com.uet.k62.web.system.examination.model.dtos.UserFormRegistrationDTO;

import java.math.BigInteger;

public interface UserService {
	RestBody registerAccount(UserFormRegistrationDTO userFormRegistrationDTO);
	
	RestBody getAllUsers();

	RestBody getUser(BigInteger id);

	RestBody deleteUser(BigInteger id);

	RestBody updateInfoUser(UserDetailDTO userDetailDTO, BigInteger id);
}
