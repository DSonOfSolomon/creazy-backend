package com.creazy.service;

import com.creazy.domain.entity.PostingStreak;
import com.creazy.repository.PostingStreakRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PostingStreakService {

    private final PostingStreakRepository repository;

    public PostingStreakService(PostingStreakRepository repository) {
        this.repository = repository;
    }

    public void updateStreak(LocalDate newPostDate) {
        // Get the streak (assume only one row for now)
        PostingStreak streak = repository.findAll()
                .stream()
                .findFirst()
                .orElseGet(this::initializeStreak);

        if (streak.getLastPostDate() != null) {
            long daysBetween = ChronoUnit.DAYS.between(streak.getLastPostDate(), newPostDate);

            if (daysBetween == 1) {
                streak.setCurrentStreak(streak.getCurrentStreak() + 1);
            } else if (daysBetween > 1) {
                streak.setMissedDays(streak.getMissedDays() + (int) daysBetween - 1);
                streak.setCurrentStreak(1);
            }
        } else {
            streak.setCurrentStreak(1);
        }

        streak.setLastPostDate(newPostDate);

        if (streak.getCurrentStreak() > streak.getLongestStreak()) {
            streak.setLongestStreak(streak.getCurrentStreak());
        }

        repository.save(streak);
    }

    private PostingStreak initializeStreak() {
        PostingStreak streak = new PostingStreak();
        streak.setCurrentStreak(0);
        streak.setLongestStreak(0);
        streak.setMissedDays(0);
        return repository.save(streak);
    }

    public PostingStreak getCurrentStreak(){
        return repository.findAll()
                .stream()
                .findFirst()
                .orElseGet(this::initializeStreak);
    }
}
