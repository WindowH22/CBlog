package com.fastcampus.biz.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.domain.Post;
import com.fastcampus.biz.persistence.CategoryRepository;
import com.fastcampus.biz.persistence.PostRepository;
import com.fastcampus.web.dto.PostDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	
	private final PostRepository postRepository;
	private final CategoryRepository categoryRepository;
	
	// 포스트 목록
	public List<Post> getPostList(Model model){
		Blog blog = (Blog)model.getAttribute("blog");
		return postRepository.findByblogId(blog.getBlogId());
	}
	
	// 포스트 목록(카테고리 클릭시)
	public List<Post> getPostListByCategoryId(int id){
		return postRepository.findByCategoryId(id);
	}
	
	// 포스트 삭제
	@Transactional
	public void deletePost(int id) {
		postRepository.deleteById(id);
	}

	// 포스트 등록
	@Transactional
	public void insertPost(PostDto dto, Model model,HttpSession session) {
		System.out.println("포스트 등록동작");
		Post newPost = new Post();
		Blog findBlog = (Blog)session.getAttribute("blog");
		System.out.println(dto.getCategoryId());
		Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
		if(category.isPresent()) {
			newPost.setCategoryId(category.get().getCategoryId());
		}
		
		newPost.setBlogId(findBlog.getBlogId());
		newPost.setTitle(dto.getTitle());
		newPost.setContent(dto.getContent());
		postRepository.save(newPost);
	}
	
	//포스트 상세조회
	public Post getPost(int id) {
		return postRepository.findById(id).get();
	}

	// 포스트 업데이트
	@Transactional
	public void updatePost(PostDto dto) {
		Optional<Post> findPost = postRepository.findByPostId(dto.getPostId());
		Optional<Category> category = categoryRepository.findById(dto.getCategoryId());
		
		if(findPost.isPresent()) {
			Post updatePost = findPost.get();
			updatePost.toString();
			if(category.isPresent()) {
				updatePost.setCategoryId(category.get().getCategoryId());
			}
			updatePost.setTitle(dto.getTitle());
			updatePost.setContent(dto.getContent());
			updatePost.toString();
			postRepository.save(updatePost);
		}
	}
	
}
