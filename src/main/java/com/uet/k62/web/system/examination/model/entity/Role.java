package com.uet.k62.web.system.examination.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "role")
public class Role extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
}
