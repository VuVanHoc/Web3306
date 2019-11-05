package com.uet.k62.web.system.examination.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity(name = "exam_result")
public class ExamResult extends BaseEntity {

	@Column(name = "user_id")
	private int userId;

	@Column(name = "schedule_id")
	private int courseId;
	
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "status")
	private boolean status;
	
}
