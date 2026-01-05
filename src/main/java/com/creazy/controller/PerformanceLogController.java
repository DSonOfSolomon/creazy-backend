package com.creazy.controller;


import com.creazy.domain.entity.PerformanceLog;
import com.creazy.service.PerformanceLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceLogController {
    private final PerformanceLogService service;

    public PerformanceLogController(PerformanceLogService service){
        this.service=service;
    }

    //add new log
    @PostMapping
    public ResponseEntity<PerformanceLog> createLog(@RequestBody PerformanceLog log){
        PerformanceLog saved = service.createdLog(log);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    //Get all logs
    @GetMapping
    public List<PerformanceLog> getAllLogs(){
        return service.getAllLogs();
    }
    @GetMapping("/post/{postId}")
    public List<PerformanceLog> getLogsByPost(@PathVariable Long postId){
        return service.getLogsByPostId(postId);
    }
}
