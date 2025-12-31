package com.creazy.controller;

import com.creazy.domain.entity.ContentPost;
import com.creazy.domain.enums.ContentType;
import com.creazy.domain.enums.Platform;
import com.creazy.service.ContentPostService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<ContentPost> updatedPost(
            @PathVariable Long id,
            @Valid @RequestBody ContentPost updatedPost) {

        Optional<ContentPost> existingPost = contentPostService.getPostById(id);
        if (existingPost.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ContentPost post = existingPost.get();
        post.setTitle(updatedPost.getTitle());
        post.setIdea(updatedPost.getIdea());
        post.setPlatform(updatedPost.getPlatform());
        post.setContentType(updatedPost.getContentType());
        post.setTargetEmotion(updatedPost.getTargetEmotion());
        post.setPlannedDate(updatedPost.getPlannedDate());

        ContentPost savedPost = contentPostService.updatePost(post);
        return ResponseEntity.ok(savedPost);
    }


    /**
     * Delete a post by ID
     * DELETE /api/posts/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (!contentPostService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        contentPostService.deletePost(id);
        return ResponseEntity.noContent().build();
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
        List<ContentPost> posts = contentPostService.getPostsPlannedAfter(LocalDate.from(dt));
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/filter")
    public List<ContentPost> filterPosts(
            @RequestParam(required = false) Platform platform,
            @RequestParam(required = false) ContentType contentType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate plannedDate
    ) {
        if (platform != null && contentType != null) {
            return contentPostService.getPostsByPlatformAndContentType(platform, contentType);
        } else if (platform != null) {
            return contentPostService.getPostsByPlatform(platform);
        } else if (contentType != null) {
            return contentPostService.getPostsByContentType(contentType);
        } else if (plannedDate != null) {
            return contentPostService.getPostsByPlannedDate(plannedDate);
        } else {
            return contentPostService.getAllPosts();
        }
    }

}