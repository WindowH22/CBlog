package com.fastcampus.biz.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.biz.domain.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	Optional<Blog> findByTitle(String title);
	Optional<Blog> findByTag(String tag);
	List<Blog> findByTitleContaining(String title);
	List<Blog> findByTagContaining(String tag);
}
