package com.storyverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storyverse.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByUserId(String userId);
}