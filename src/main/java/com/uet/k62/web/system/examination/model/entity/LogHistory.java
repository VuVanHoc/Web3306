package com.uet.k62.web.system.examination.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "log_history")
public class LogHistory extends BaseEntity {

	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "action")
	private String action;
}
