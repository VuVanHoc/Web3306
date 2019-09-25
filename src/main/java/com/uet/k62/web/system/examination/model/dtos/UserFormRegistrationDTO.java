package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserFormRegistrationDTO {
	private BigInteger roleId ;
	private String username;
	private String password;
	private String confirmPassword;
	private String fullName;
	private String birthday;
	private String email;
	private String phone;

}
