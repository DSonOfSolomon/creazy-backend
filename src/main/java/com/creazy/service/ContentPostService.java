package com.creazy.service;

import com.creazy.domain.entity.ContentPost;
import com.creazy.domain.enums.Platform;
import com.creazy.domain.enums.ContentType;
import com.creazy.repository.ContentPostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for ContentPost entity
 */
@Service
public class ContentPostService {

    private final ContentPostRepository contentPostRepository;

    // Constructor injection
    public ContentPostService(ContentPostRepository contentPostRepository){
        this.contentPostRepository = contentPostRepository;
    }

    /**
     * Save a new ContentPost
     */
    public ContentPost createPost(ContentPost post){
        return contentPostRepository.save(post);
    }

    /**
     * Get a post by ID
     */
    public Optional<ContentPost> getPostById(Long id){
        return contentPostRepository.findById(id);
    }

    /**
     * Get all posts
     */
    public List<ContentPost> getAllPosts(){
        return contentPostRepository.findAll();
    }

    /**
     * Update an existing post
     */
    public ContentPost updatePost(ContentPost post){
        return contentPostRepository.save(post);
    }

    /**
     * Delete a post by ID
     */
    public void deletePost(Long id){
        contentPostRepository.deleteById(id);
    }

    /**
     * Check if a post exists
     */
    public boolean existsById(Long id) {
        return contentPostRepository.existsById(id);
    }

    /**
     * Get posts for a specific platform
     */
    public List<ContentPost> getPostsByPlatform(Platform platform){
        return contentPostRepository.findByPlatform(platform);
    }

    /**
     * Get posts for a specific content type
     */
    public List<ContentPost> getPostsByContentType(ContentType contentType){
        return contentPostRepository.findByContentType(contentType);
    }

    /**
     * Get posts for a specific planned date
     */
    public List<ContentPost> getPostsByPlannedDate(LocalDate plannedDate){
        return contentPostRepository.findByPlannedDate(plannedDate);
    }

    /**
     * Get posts filtered by platform AND content type
     */
    public List<ContentPost> getPostsByPlatformAndContentType(Platform platform, ContentType contentType){
        return contentPostRepository.findByPlatformAndContentType(platform, contentType);
    }

    /**
     * Get posts planned after a certain date (if needed)
     */
    public List<ContentPost> getPostsPlannedAfter(LocalDate date){
        return contentPostRepository.findByPlannedDateAfter(date);
    }
}
