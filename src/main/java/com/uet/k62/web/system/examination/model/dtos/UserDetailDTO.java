package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;

@Data
public class UserDetailDTO {
	private String fullName;
	private String birthday;
	private String email;
//	private String username;
	private String phone;
	private String picture;
}
