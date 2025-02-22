package com.developers.user.controllers;

import com.developers.user.entities.Post;
import com.developers.user.services.PostService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

        private final PostService postService;

        public PostController(PostService postService) {
            this.postService = postService;
        }

        @PostMapping("/{userId}")
        public Post createPost(@PathVariable Long userId, @RequestBody Post post) {
            return postService.createPost(userId, post);
        }

        @GetMapping
        public List<Post> getAllPosts() {
            return postService.getAllPosts();
        }

        @GetMapping("/user/{userId}")
        public List<Post> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
        }

        @PutMapping("/{postId}")
        public Post updatePost(@PathVariable Long postId, @RequestBody Post post) {
        return postService.updatePost(postId, post);
        }

        @DeleteMapping("/{postId}")
        public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        }
}
