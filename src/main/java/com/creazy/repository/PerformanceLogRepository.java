package com.creazy.repository;

import com.creazy.domain.entity.PerformanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceLogRepository extends JpaRepository<PerformanceLog, Long> {

    List<PerformanceLog> findByPostId(Long postId);
}
