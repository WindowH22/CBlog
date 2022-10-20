package com.fastcampus.biz.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.fastcampus.biz.domain.BlogUser;
import com.fastcampus.biz.persistence.BlogRepository;
import com.fastcampus.biz.persistence.BlogUserRepository;
import com.fastcampus.web.dto.BlogUserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogUserService {
	
	private final BlogUserRepository blogUserRepository;
	
	//로그인
	public void login( BlogUserDto userDto, HttpSession session) {
		
		BlogUser findUser = blogUserRepository.findByUsername(userDto.getUsername()).get();
		
		if(findUser.getPassword().equals(userDto.getPassword())) {
			session.setAttribute("user", findUser);
		}
	}
}
