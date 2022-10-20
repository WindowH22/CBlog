package com.fastcampus.web.dto;

import lombok.Data;

@Data
public class PostDto {

	private int postId;
	private int categoryId;
	private String title;	
	private String Content;

}
