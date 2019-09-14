package com.uet.k62.web.system.examination.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public BigInteger id;

	@Column(name = "created_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy", timezone = "GMT+07")
	protected Date createdDate;

	@Column(name = "created_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy", timezone = "GMT+07")
	protected Date updatedDate;

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
