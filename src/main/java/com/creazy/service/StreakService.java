package com.creazy.service;


import com.creazy.domain.entity.PostingStreak;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class StreakService {

    public void updateStreak(PostingStreak streak, LocalDate postDate){
        if(streak.getLastPostDate() ==null){
            streak.setCurrentStreak(1);
            streak.setLongestStreak(1);
        }else{
            long daysBetween = ChronoUnit.DAYS.between(
                    streak.getLastPostDate(),
                    postDate
            );
            if(daysBetween == 1){
                streak.setCurrentStreak(streak.getCurrentStreak() + 1);
            }else if(daysBetween > 1){
                streak.setMissedDays(
                        streak.getMissedDays() + (int) (daysBetween -1)
                );
                streak.setCurrentStreak(1);
            }
        }
        //Update longest streak
        if(streak.getCurrentStreak()> streak.getLongestStreak()){
            streak.setLongestStreak(streak.getCurrentStreak());
        }
        streak.setLastPostDate(postDate);
    }
}
