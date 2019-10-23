package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
	
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
