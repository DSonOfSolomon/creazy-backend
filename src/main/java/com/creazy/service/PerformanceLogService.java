package com.creazy.service;


import com.creazy.domain.entity.PerformanceLog;
import com.creazy.repository.PerformanceLogRepository;
import org.aspectj.weaver.patterns.PerFromSuper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceLogService {
    private final PerformanceLogRepository repository;

    public PerformanceLogService(PerformanceLogRepository repository){
        this.repository = repository;
    }

    //Save a new performance log
    public PerformanceLog createdLog(PerformanceLog log){

        return repository.save(log);
    }

    //Get all logs
    public List<PerformanceLog> getAllLogs(){
        return repository.findAll();
    }
    //Get logs for a specific post
    public List<PerformanceLog> getLogsByPostId(Long postId){

        return repository.findByPostId(postId);
    }
}
