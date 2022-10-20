package com.fastcampus.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.service.BlogService;
import com.fastcampus.biz.service.CategoryService;
import com.fastcampus.biz.service.PostService;
import com.fastcampus.web.dto.BlogDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogController {

	private final BlogService blogService;
	private final PostService postService;
	private final CategoryService categoryService;
	
	//블로그 등록 패이지로 이동
	@GetMapping("insertBlog")
	public String insertBlog(){
		return "blog/insertBlog";
	}

	// 블로그 메인 페이지로 이동(리스트)
	@GetMapping("blog/{id}")
	public String blogMain1(@PathVariable int id,Model model,HttpSession session,@PageableDefault(size = 5, sort = "postId", direction = Direction.DESC ) Pageable pageable) {
		blogService.getBlog(id,model);
		model.addAttribute("categoryList", categoryService.getCategoryList(model));
		model.addAttribute("postList",postService.getPostList(model));
		return "/blog/blogMain";
	}
	
	// 블로그 관리 페이지로 이동
	@GetMapping("blog/{id}/getBlog")
	public String getBlog(@PathVariable int id,Model model) {
		blogService.getBlog(id,model);
		blogService.isUpdate(model);
		return "blog/getBlog";
	}
	
	// 블로그 삭제확인 페이지 팝업창 띄우기
	@GetMapping("blog/{id}/deleteRequest")
	public String deleteRequest(@PathVariable int id,Model model) {
		blogService.getBlog(id,model);
		return "blog/deleteRequest";
	}
	
	// 블로그 수정(제목,태그)
	@PostMapping("blog/{id}/updateBlog")
	public String updateBlog(@PathVariable int id, BlogDto dto,HttpSession session,Model model) {
		blogService.updateBlog(dto,session,model);
		return "redirect:/blog/"+id;
	}
	
	// 블로그 등록
	@PostMapping("insertBlog/{userId}")
	public String insertBlog(@PathVariable int userId,BlogDto blogDto, HttpSession session) throws Exception {
		blogService.insertBlog(blogDto, session);
		return "redirect:/blog/"+userId;
	}
	
	// 블로그 검색
	@PostMapping("searchBlog")
	public String searchBlog(BlogDto blogDto, Model model) {
		model.addAttribute("findBlogList", blogService.serch(blogDto, model));
		return "welcome";
	}
	
	// 블로그 삭제요청
	@GetMapping("deleteRequest/{blogId}")
	public String deleteRequest(@PathVariable int blogId) {
		System.out.println("삭제요청 보냄");
		blogService.deleteRequest(blogId);
		System.out.println("삭제요청 전송완료");
		return "redirect:/logout";
	}
	
	// 블로그 삭제
	@GetMapping("deleteBlog/{blogId}")
	public String deleteBlog(@PathVariable int blogId) {
		blogService.deleteBlog(blogId);
		return "redirect:/";
	}
	
}
