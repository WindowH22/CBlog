package com.fastcampus.biz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Blog {

	@Id
	private int blogId;
	@Column
	private String status;
	@Column
	private String tag;
	@Column
	private String title;
	
}
