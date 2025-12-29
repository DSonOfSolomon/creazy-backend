package com.creazy.service;


import com.creazy.domain.entity.ContentPost;
import com.creazy.domain.enums.Platform;
import com.creazy.repository.ContentPostRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * Service layer for ContentPost entity
 */
@Service
public class ContentPostService {
    private final ContentPostRepository contentPostRepository;

    //Constructor injection is preferred over field injection
    public ContentPostService(ContentPostRepository contentPostRepository){
        this.contentPostRepository = contentPostRepository;
    }

    /**
     * Save a new ContentPost
     * @return contentPostRepository
     */
    public ContentPost createPost(ContentPost post){
        //Set timestamps before saving
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return contentPostRepository.save(post);
    }

    /**
     * Get all post by ID
     */
    public Optional<ContentPost> getPOstById(Long id){
        return contentPostRepository.findById(id);
    }

    /**
     * Get all posts
     */
    public List<ContentPost> getAllPosts(){
        return contentPostRepository.findAll();
    }

    /**
     * update an existing post
     */
    public ContentPost updatePost(ContentPost post){
        post.setUpdatedAt(LocalDateTime.now());
        return contentPostRepository.save(post);
    }

    /**
     * Delete a post by ID
     */
    public void deletePost(Long id){
        contentPostRepository.deleteById(id);
    }

    /**
     * Get all posts for a specific platform
     */
    public List<ContentPost> getPostsByPlatform(Platform platform){
        return contentPostRepository.findByPlatform(platform);
    }
    /**
     * Get all posts planned after a certain date
     */
    public List<ContentPost> getPostsPlannedAfter(LocalDateTime dateTime){
        return contentPostRepository.findByPlannedDateAfter(dateTime);
    }
}
