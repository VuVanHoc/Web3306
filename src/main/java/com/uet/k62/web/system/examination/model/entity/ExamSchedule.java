package com.uet.k62.web.system.examination.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity(name = "exam_schedule")
public class ExamSchedule extends BaseEntity {

	@Column(name = "course_id")
	private int courseId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "GMT+07")
	@Column(name = "start_time")
	private Date startTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "GMT+07")
	@Column(name = "end_time")
	private Date endTime;
	
	@Column(name = "note")
	private String note;
	
}
