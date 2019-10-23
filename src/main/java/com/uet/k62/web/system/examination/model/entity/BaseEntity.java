package com.uet.k62.web.system.examination.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public BigInteger id;

	@Getter
	@Column(name = "created_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+07")
	protected Date createdDate;

	@Setter
	@Getter
	@Column(name = "updated_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT+07")
	protected Date updatedDate;

	@Setter
	@Getter
	@Column(name = "deleted")
	protected boolean deleted;

	public BigInteger getId() {
		return this.id;
	}

	BaseEntity() {
		this.createdDate = new Date();
		this.updatedDate = new Date();
	}
}
