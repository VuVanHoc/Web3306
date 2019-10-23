package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType = "Bearer";
	
	public JwtAuthenticationResponse(String accessToken){
		this.accessToken = accessToken;
	}
}
