package com.creazy.controller;

import com.creazy.domain.entity.ContentPost;
import com.creazy.domain.enums.Platform;
import com.creazy.service.ContentPostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping ("/api/posts") // Base path for all endpoints in this controller

/**
 * REST API controller for ContentPost operations
 */
public class ContentPostController {
    private final ContentPostService contentPostService;

    //Constructor injection for the service

    public ContentPostController(ContentPostService contentPostService) {
        this.contentPostService = contentPostService;
    }

    /**
     * Create a new post
     *
     * @param post /api/posts
     * @return ...
     */
    @PostMapping
    public ResponseEntity<ContentPost> createPost(
            @Valid @RequestBody ContentPost post) {
        ContentPost savedPost = contentPostService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }


    /**
     * Get all posts
     * GET /api/posts
     *
     * @return ...
     */
    @GetMapping
    public ResponseEntity<List<ContentPost>> getAllPostA() {
        List<ContentPost> posts = contentPostService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Get a post by ID
     * GET /api/posts/{id}
     *
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContentPost> getPostById(@PathVariable Long id) {
        Optional<ContentPost> post = contentPostService.getPostById(id);
        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update a post
     * PUT /api/posts/{id}
     *
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContentPost> updatedPost(@PathVariable Long id, @RequestBody ContentPost updatedPost) {
        //Optional: fetch existing post to check if exists
        Optional<ContentPost> existingPost = contentPostService.getPostById(id);
        if (existingPost.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedPost.setId(id); //ensure the ID is set
        ContentPost savedPost = contentPostService.updatePost(updatedPost);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    /**
     * Delete a post by ID
     * DELETE /api/posts/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        contentPostService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get posts by platform
     * GET /api/posts/platform/{platform}
     */
    @GetMapping("/platform/{platform}")
    public ResponseEntity<List<ContentPost>> getPostsByPlatform(@PathVariable Platform platform) {
        List<ContentPost> posts = contentPostService.getPostsByPlatform(platform);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * Get posts planned after a specific date
     * GET /api/posts/planned-after/{dateTime}
     */
    @GetMapping("/planned-after/{dateTime}")
    public ResponseEntity<List<ContentPost>> getPostsPlannedAfter(@PathVariable String dateTime) {
        LocalDateTime dt = LocalDateTime.parse(dateTime); //ISO-8601 format
        List<ContentPost> posts = contentPostService.getPostsPlannedAfter(dt);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}