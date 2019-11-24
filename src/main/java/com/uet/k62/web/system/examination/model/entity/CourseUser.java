package com.uet.k62.web.system.examination.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "course_user")
public class CourseUser extends BaseEntity {
	
	@Column(name = "course_id")
	private Integer courseId;
	
	@Column(name = "user_id")
	private Integer userId;
}
