package com.fastcampus.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fastcampus.biz.service.BlogService;
import com.fastcampus.biz.service.CategoryService;
import com.fastcampus.biz.service.PostService;
import com.fastcampus.web.dto.PostDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	
	private final BlogService blogService;
	private final PostService postService;
	private final CategoryService categoryService;
	
	//글 상세조회 및 수정 페이지로 이동
	@GetMapping("blog/{blogId}/updatePost/{postId}")
	public String updatePost(@PathVariable int blogId,@PathVariable int postId, Model model) {
		blogService.getBlog(blogId, model);
		model.addAttribute("post", postService.getPost(postId));
		model.addAttribute("categoryList", categoryService.getCategoryList(model));
		return "post/getPost";
	}
	
	//글 작성 페이지로 이동
	@GetMapping("blog/{id}/insertPost")
	public String insertPost(@PathVariable int id, Model model) {
		blogService.getBlog(id, model);
		blogService.isUpdate(model);
		model.addAttribute("categoryList", categoryService.getCategoryList(model));
		return "post/insertPost";
	}
	
	// 글 등록
	@PostMapping("blog/{id}/insertPost")
	public String insertPost(@PathVariable int id, PostDto dto,Model model,HttpSession session) {
		postService.insertPost(dto,model,session);
		return "redirect:/blog/"+id;
	}
	
	// 글 수정
	@PostMapping("blog/{id}/updatePost")
	public String updatePost(@PathVariable int id,PostDto dto) {
		postService.updatePost(dto);
		return "redirect:/blog/"+id;
	}
	
	// 글 삭제
	@GetMapping("blog/{id}/deletePost/{postId}")
	public String deletePost(@PathVariable int id, @PathVariable int postId) {
		postService.deletePost(postId);
		return "redirect:/blog/"+id;
	}

}
