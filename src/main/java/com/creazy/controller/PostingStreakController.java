package com.creazy.controller;


import com.creazy.domain.entity.PostingStreak;
import com.creazy.service.PostingStreakService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostingStreakController {

    private final PostingStreakService postingStreakService;

    public PostingStreakController(PostingStreakService postingStreakService){
        this.postingStreakService = postingStreakService;
    }

    @GetMapping("api/streak")
    public PostingStreak getCurrentStreak(){

        return postingStreakService.getCurrentStreak();
    }

}
