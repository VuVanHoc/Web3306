package com.uet.k62.web.system.examination.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailDTO {
	
	private int index;
	private int id;
	private String fullName;
	private String birthday;
	private String email;
	private String phone;
	private String roleName;
	private int roleId;
}
