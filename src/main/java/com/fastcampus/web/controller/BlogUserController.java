package com.fastcampus.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fastcampus.biz.service.BlogUserService;
import com.fastcampus.web.dto.BlogUserDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BlogUserController {

	private final BlogUserService blogUserService;

	// 로그인 페이지로 이동
	@GetMapping("/login")
	public String login() {
		return "system/login";
	}
	
	// 로그인
	@PostMapping("/login")
	public String login(BlogUserDto userDto, HttpSession session) {
		blogUserService.login(userDto, session);
		return "redirect:/";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
