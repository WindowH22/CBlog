package com.fastcampus.biz.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.persistence.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	// 카테고리 생성(블로그 생성시)
	@Transactional
	public void insertCategory(Blog blog, Category category) {

		category.setBlogId(blog.getBlogId());
		category.setCategoryName("미분류");
		category.setDescription("기본으로 제공되는 카테고리입니다.");
		category.setDisplayType("제목 + 내용");

		categoryRepository.save(category);
	}

	// 카테고리 생성(수동)
	@Transactional
	public void insertCategory(HttpSession session,Category category) {

		Blog blog = (Blog)session.getAttribute("blog");
		String title = "TITLE";
		String mixed = "MIXED";
		
		category.setBlogId(blog.getBlogId());
		category.setCategoryName(category.getCategoryName());
		category.setDescription(category.getDescription());
		if(category.getDisplayType().equals(title)) {
			category.setDisplayType("제목");
		}else if(category.getDisplayType().equals(mixed)){
			category.setDisplayType("제목 + 내용");
		}
		categoryRepository.save(category);
	}

	// 카테고리 목록조회
	public List<Category> getCategoryList(Model model) {
		
		Blog findBlog = (Blog) model.getAttribute("blog");
		return categoryRepository.findByBlogId(findBlog.getBlogId());
	}
	

	// 카테고리 상세조회
	public Category getCategory(int categoryId) {
		return categoryRepository.findById(categoryId).get();
	}
	
	// 카테고리 수정
	@Transactional
	public void updateCategory(Category category) {
		Optional<Category> findCategory = categoryRepository.findById(category.getCategoryId());
		
		if(findCategory.isPresent()) {
			Category updateCategory = findCategory.get();
			updateCategory.setCategoryName(category.getCategoryName());
			updateCategory.setDescription(category.getDescription());
			
			// 보이기 유형 설정
			if(category.getDisplayType().equals("TITLE")) {
				updateCategory.setDisplayType("제목");
			}else {
				updateCategory.setDisplayType("제목 + 내용");
			}
		}
	}
	
	// 카테고리 삭제
	public void deleteCategory(int categoryId) {
		categoryRepository.deleteById(categoryId);
	}

}
