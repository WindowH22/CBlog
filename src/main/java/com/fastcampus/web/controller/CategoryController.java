package com.fastcampus.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.service.BlogService;
import com.fastcampus.biz.service.CategoryService;
import com.fastcampus.biz.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CategoryController {
	
	private final PostService postService;
	private final BlogService blogService;
	private final CategoryService categoryService;

	// 카테고리 등록 및 수정 페이지로 이동
	@GetMapping("blog/{id}/getCategoryList")
	public String getCategoryList(@PathVariable int id, Model model) {
		blogService.getBlog(id, model);
		blogService.isUpdate(model);
		model.addAttribute("categoryList", categoryService.getCategoryList(model));
		return "category/getCategoryList";
	}

	// 카테고리 수정
	@GetMapping("blog/{id}/updateCategory/{categoryId}")
	public String updateCategory(@PathVariable int id, @PathVariable int categoryId, Model model) {
		blogService.getBlog(id, model);
		blogService.isUpdate(model);
		model.addAttribute("categoryList", categoryService.getCategoryList(model));
		model.addAttribute("updateCategory", categoryService.getCategory(categoryId));
		return "category/getCategoryList";
	}

	// 카테고리로 포스트 검색
	@GetMapping("blog/{id}/find/{categoryId}")
	public String blogMain(@PathVariable int id, @PathVariable int categoryId, Model model) {
		blogService.getBlog(id, model);
		model.addAttribute("categoryList", categoryService.getCategoryList(model));
		model.addAttribute("findPostList", postService.getPostListByCategoryId(categoryId));
		return "blog/blogMain";
	}

	// 카테고리 등록
	@PostMapping("blog/{id}/insertCategory")
	public String insertCategory(@PathVariable int id, HttpSession session, Category category) {
		categoryService.insertCategory(session, category);
		return "redirect:/blog/" + id + "/getCategoryList";
	}

	// 카테고리 수정
	@PostMapping("blog/{id}/updateCategory/updateCategory")
	public String updateCategory(@PathVariable int id, Category category) {
		categoryService.updateCategory(category);
		return "redirect:/blog/" + id + "/getCategoryList";
	}

	// 카테고리 삭제
	@GetMapping("blog/{id}/deleteCategory/{categoryId}")
	public String deleteCategory(@PathVariable int id, @PathVariable int categoryId) {
		categoryService.deleteCategory(categoryId);
		return "redirect:/blog/" + id + "/getCategoryList";
	}
}
