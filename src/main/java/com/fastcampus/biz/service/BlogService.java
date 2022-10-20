package com.fastcampus.biz.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.BlogUser;
import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.persistence.BlogRepository;
import com.fastcampus.biz.persistence.CategoryRepository;
import com.fastcampus.biz.persistence.PostRepository;
import com.fastcampus.web.dto.BlogDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogService {

	private final BlogRepository blogRepository;
	private final CategoryRepository categoryRepository;
	private final PostRepository postRepository;
	private final CategoryService categoryService;

	// 블로그 등록
	@Transactional
	public void insertBlog(BlogDto blogDto, HttpSession session) throws Exception {

		BlogUser loginUser = (BlogUser) session.getAttribute("user");
		Optional<Blog> findBlogTitle = blogRepository.findByTitle(blogDto.getTitle());

		// 같은 블로그제목 중복 불가
		if (findBlogTitle.isPresent()) {
			throw new Exception("같은 제목의 블로그가 이미 존재합니다.");
		} else {
			Blog blog = new Blog();
			// 블로그 아이디와 블로그 유저아이디는 같다
			blog.setBlogId(loginUser.getUserId());
			blog.setStatus("운영");
			blog.setTag("No tags");
			blog.setTitle(blogDto.getTitle());
			blogRepository.save(blog);

			// 블로그가 생성될 때 카테고리 테이블도 하나 생성된다.
			Category category = new Category();
			categoryService.insertCategory(blog, category);

			session.setAttribute("category", category);
			session.setAttribute("blog", blog);
		}
	}

	// 블로그 관리페이지임을 확인하는 메서드
	public void isUpdate(Model model) {
		BlogDto dto = new BlogDto();
		dto.setUpdateBlog(true);
		model.addAttribute("isUpdate", dto.isUpdateBlog());
	}

	// 블로그 검색
		@Transactional
		public List<Blog> serch(BlogDto blogDto, Model model) {

			if (blogDto.getSearchCondition() == null)
				blogDto.setSearchCondition("TITLE");
			
			System.out.println("SearchConditon: "+ blogDto.getSearchCondition());
			System.out.println("searchKeyWord: "+blogDto.getSearchKeyword());
			
			model.addAttribute("condition", blogDto.getSearchCondition());
			model.addAttribute("keyword", blogDto.getSearchKeyword());

			// 제목
			if (blogDto.getSearchCondition().equals("TITLE")) {
				if (blogDto.getSearchKeyword() == "") {
					return null;
				}
				return blogRepository.findByTitleContaining(blogDto.getSearchKeyword());
			}
			// 태그
			else if(blogDto.getSearchCondition().equals("TAG")) {
				return blogRepository.findByTagContaining(blogDto.getSearchKeyword());
			}
			return null;
		}


	// 블로그 목록 조회
	public List<Blog> getBlogList() {
		return blogRepository.findAll();
	}

	// 블로그 상세 조회
	public void getBlog(int id, Model model) {
		Optional<Blog> blog = blogRepository.findById(id);
		if (blog.isPresent()) {
			model.addAttribute("blog", blog.get());
		}
	}

	// 블로그 수정(제목,태그)
	@Transactional
	public void updateBlog(BlogDto dto, HttpSession session, Model model) {

		Blog blog = (Blog) session.getAttribute("blog");
		Optional<Blog> findBlog = blogRepository.findById(blog.getBlogId());
		if (findBlog.isPresent()) {
			Blog updateBlog = findBlog.get();
			updateBlog.setTitle(dto.getTitle());
			updateBlog.setTag(dto.getTag());
			blogRepository.save(updateBlog);
			model.addAttribute("blog", updateBlog);
		}

	}

	// 블로그 삭제요청
	@Transactional
	public void deleteRequest(int blogId) {
		Optional<Blog> findBlog = blogRepository.findById(blogId);
		if (findBlog.isPresent()) {
			Blog deleteBlog = findBlog.get();
			deleteBlog.setStatus("삭제 요청");
			blogRepository.save(deleteBlog);
		}
	}

	// 블로그 삭제
	@Transactional
	public void deleteBlog(int blogId) {
		categoryRepository.deleteByBlogId(blogId);
		postRepository.deleteByBlogId(blogId);
		blogRepository.deleteById(blogId);
	}

}
