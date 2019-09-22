package com.uet.k62.web.system.examination.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "question")
public class Question extends BaseEntity {
	
	@Column(name = "question_type")
	private int questionTypeId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "image_url")
	private String imageUrl;
}
