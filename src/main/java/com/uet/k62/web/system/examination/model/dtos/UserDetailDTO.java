package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailDTO {
	
	private int index;
	private String id;
	private String fullName;
	private String birthday;
	private String email;
	private String phone;
	private String address;
	private String roleName;
	
}
