package com.fastcampus.biz.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	@Column(nullable = false)
	private int blogId;
	@Column(nullable = false)
	private int categoryId;
	@Column
	private String content;
	@Column
	private String title;
	@CreationTimestamp
	private Timestamp createDate;
	@UpdateTimestamp
	private Timestamp modifiedDate;
	
}
