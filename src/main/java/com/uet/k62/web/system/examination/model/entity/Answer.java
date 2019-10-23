package com.uet.k62.web.system.examination.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigInteger;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answer")
public class Answer extends BaseEntity {

	@Column(name = "question_id")
	private BigInteger questionId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "status")
	private boolean status;
}
