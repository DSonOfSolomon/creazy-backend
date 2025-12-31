package com.creazy.repository;

import com.creazy.domain.entity.ContentPost;
import com.creazy.domain.enums.ContentType;
import com.creazy.domain.enums.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContentPostRepository extends JpaRepository<ContentPost, Long> {

    // Find all posts for a given platform
    List<ContentPost> findByPlatform(Platform platform);

    // Find all posts planned after a given date
    List<ContentPost> findByPlannedDateAfter(LocalDate date);

    // Find all posts of a specific content type
    List<ContentPost> findByContentType(ContentType contentType);

    // Optional: combine platform + contentType filtering
    List<ContentPost> findByPlatformAndContentType(Platform platform, ContentType contentType);

    // Find posts for an exact planned date
    List<ContentPost> findByPlannedDate(LocalDate plannedDate);

}
