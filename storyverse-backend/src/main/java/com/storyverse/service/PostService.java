package com.storyverse.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storyverse.DTO.PostRequest;
import com.storyverse.domain.Post;
import com.storyverse.repository.PostRepository;
import com.storyverse.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }
    
    public List<Post> getPostsByUserId(String userId) {
        return postRepository.findByUserId(userId);
    }

    public void createPost(PostRequest request, String token) {
        String userId = jwtUtil.extractUserId(token);

        Post post = Post.builder()
                .title(request.getTitle())
                .postContent(request.getPostContent())
                .userId(userId)
                .isOpen(request.isOpen())
                .postDate(LocalDate.now().toString())
                .build();

        postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setPostContent(updatedPost.getPostContent());
                    post.setUserId(updatedPost.getUserId());
                    post.setPostDate(updatedPost.getPostDate());
                    post.setOpen(updatedPost.isOpen());
                    return postRepository.save(post);
                }).orElseThrow();
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

