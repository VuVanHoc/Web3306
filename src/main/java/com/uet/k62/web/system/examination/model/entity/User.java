package com.uet.k62.web.system.examination.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "user")
public class User extends BaseEntity {
	
	@Column(name = "role_id", nullable = false)
	private Integer roleId;
	
	@Column(name = "username", nullable = false, length = 50)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;

	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "picture")
	private String picture;
	
}
