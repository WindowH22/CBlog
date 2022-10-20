package com.fastcampus.biz.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.biz.domain.BlogUser;

public interface BlogUserRepository extends JpaRepository<BlogUser, Integer> {
	Optional<BlogUser> findByUsername(String username);
}
