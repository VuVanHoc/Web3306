package com.uet.k62.web.system.examination.model.entity;

import com.fasterxml.jackson.annotation.*;
import com.uet.k62.web.system.examination.utils.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_FORMAT_PATTERN, timezone = "GMT+07:00")
	private Date birthday;
	
	@Column(name = "picture")
	private String picture;

	@ManyToMany(mappedBy = "users")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Set<Course> courses;
}
