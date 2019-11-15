package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserFormRegistrationDTO {
	
	private Integer roleId ;
	private String fullName;
	private String username;
	private String password;
	private String confirmPassword;
	private String phone;

}
