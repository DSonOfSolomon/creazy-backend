package com.creazy.repository;

import com.creazy.domain.entity.ContentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContentPostRepository extends JpaRepository<ContentPost, Long> {

    //Find all post for a given platform
    List<ContentPost> findByPlatform(com.creazy.domain.enums.Platform platform);

    //Find all posts planned after a given date
    List<ContentPost> findByPlannedDateAfter(java.time.LocalDateTime dateTime);
}
