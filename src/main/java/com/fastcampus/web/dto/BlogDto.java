package com.fastcampus.web.dto;

import lombok.Data;

@Data
public class BlogDto {

	private String title;	
	private String tag;
	
	// 검색 관련 멤버변수
	private String searchKeyword;
	private String searchCondition;
	
	// 블로그 관리확인 변수
	private boolean isUpdateBlog;
}
