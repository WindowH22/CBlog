package com.fastcampus.biz.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.biz.domain.Post;
public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByCategoryId(int categoryId);
	Optional<Post> findByPostId(int postId);
	List<Post> findByblogId(int blogId);
	void deleteByBlogId(int blogId);
}
