package com.creazy.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PostingStreak {


    //Initial vars
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int CurrentStreak;
    private int LongestStreak;
    private LocalDate lastPOstDate;
    private int missedDays;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt=LocalDateTime.now();
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCurrentStreak() {
        return CurrentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        CurrentStreak = currentStreak;
    }

    public int getLongestStreak() {
        return LongestStreak;
    }

    public void setLongestStreak(int longestStreak) {
        LongestStreak = longestStreak;
    }

    public LocalDate getLastPOstDate() {
        return lastPOstDate;
    }

    public void setLastPOstDate(LocalDate lastPOstDate) {
        this.lastPOstDate = lastPOstDate;
    }

    public int getMissedDays() {
        return missedDays;
    }

    public void setMissedDays(int missedDays) {
        this.missedDays = missedDays;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
