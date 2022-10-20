package com.fastcampus.biz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="BLOG_USER")
@Data
@Entity
public class BlogUser {
	@Id
	private int userId;
	@Column (nullable = false)
	private String name;
	@Column (nullable = false)
	private String password;
	@Column (nullable = false)
	private String role;
	@Column (nullable = false)
	private String username;
}
