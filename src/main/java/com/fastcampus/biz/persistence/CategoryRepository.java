package com.fastcampus.biz.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.biz.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByBlogId(int blogId);
	Optional<Category> findByCategoryName(String categoryName);
	void deleteByBlogId(int blogId);
	
}
