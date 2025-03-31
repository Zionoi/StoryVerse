package com.storyverse.service;

import com.storyverse.domain.Post;
import com.storyverse.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());
                    post.setUserId(updatedPost.getUserId());
                    post.setDate(updatedPost.getDate());
                    post.setOpen(updatedPost.isOpen());
                    return postRepository.save(post);
                }).orElseThrow();
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

