package com.storyverse.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storyverse.DTO.PostRequest;
import com.storyverse.domain.Post;
import com.storyverse.service.PostService;
import com.storyverse.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final JwtUtil jwtUtil;

    // 전체 게시글 (관리자나 테스트용)
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // 특정 게시글 보기
    @GetMapping("/detail/{id}")
    public Optional<Post> getPost(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPostsByUserId(@PathVariable("userId") String userId,
                                              @CookieValue(name = "token", required = false) String token) {
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다.");
        }

        String tokenUserId = jwtUtil.extractUserId(token);
        if (!userId.equals(tokenUserId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("다른 사용자의 글에 접근할 수 없습니다.");
        }

        List<Post> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    // 게시글 작성
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest request,
                                        @CookieValue(name = "token", required = false) String token) {
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않은 사용자입니다.");
        }

        postService.createPost(request, token);
        return ResponseEntity.ok("게시글 작성 완료");
    }

    
    // 게시글 수정
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }
}
