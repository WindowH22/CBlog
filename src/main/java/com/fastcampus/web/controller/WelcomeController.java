package com.fastcampus.web.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.BlogUser;
import com.fastcampus.biz.persistence.BlogRepository;
import com.fastcampus.biz.service.BlogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WelcomeController {

	private final BlogService blogservice;
	private final BlogRepository blogRepository;

	@GetMapping({ "", "/" })
	public String welcome(Model model, HttpSession session) {

		// 블로그 존재여부 파악
		if (session.getAttribute("user") != null) {
			BlogUser user = (BlogUser) session.getAttribute("user");
			Optional<Blog> findBlog = blogRepository.findById(user.getUserId());

			if (findBlog.isPresent()) {
				model.addAttribute("blogUser", user);
				session.setAttribute("blog", findBlog.get());
			} else {
				model.addAttribute("NoBlogUser", user);
			}
		}

		model.addAttribute("blogList", blogservice.getBlogList());
		return "welcome";
	}

}
