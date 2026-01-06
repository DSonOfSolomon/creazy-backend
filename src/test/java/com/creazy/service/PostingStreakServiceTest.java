package com.creazy.service;

import com.creazy.domain.entity.PostingStreak;
import com.creazy.repository.PostingStreakRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostingStreakServiceTest {

    private PostingStreakRepository repo;
    private PostingStreakService service;
    private PostingStreak[] db; // Simulate database row

    @BeforeEach
    void setUp() {
        db = new PostingStreak[1]; // initially null
        repo = mock(PostingStreakRepository.class);

        // Mock findAll() to return db[0] if exists
        when(repo.findAll()).thenAnswer(invocation ->
                db[0] == null ? Collections.emptyList() : Collections.singletonList(db[0])
        );

        // Mock save() to store object in db[0]
        when(repo.save(any(PostingStreak.class))).thenAnswer(invocation -> {
            db[0] = invocation.getArgument(0);
            return db[0];
        });

        service = new PostingStreakService(repo);
    }

    @Test
    void firstPostStartsStreak() {
        LocalDate today = LocalDate.of(2025, 1, 10);

        service.updateStreak(today);

        PostingStreak streak = service.getCurrentStreak();
        assertEquals(1, streak.getCurrentStreak());
        assertEquals(1, streak.getLongestStreak());
        assertEquals(today, streak.getLastPostDate());
        assertEquals(0, streak.getMissedDays());
    }

    @Test
    void consecutivePostIncrementsStreak() {
        LocalDate day1 = LocalDate.of(2025, 1, 10);
        LocalDate day2 = LocalDate.of(2025, 1, 11);

        service.updateStreak(day1);
        service.updateStreak(day2);

        PostingStreak streak = service.getCurrentStreak();
        assertEquals(2, streak.getCurrentStreak());
        assertEquals(2, streak.getLongestStreak());
        assertEquals(day2, streak.getLastPostDate());
        assertEquals(0, streak.getMissedDays());
    }

    @Test
    void missedDayResetsCurrentStreak() {
        LocalDate day1 = LocalDate.of(2025, 1, 10);
        LocalDate day3 = LocalDate.of(2025, 1, 12); // skipped 1 day

        service.updateStreak(day1);
        service.updateStreak(day3);

        PostingStreak streak = service.getCurrentStreak();
        assertEquals(1, streak.getCurrentStreak()); // reset to 1
        assertEquals(1, streak.getLongestStreak()); // longest still 1
        assertEquals(day3, streak.getLastPostDate());
        assertEquals(1, streak.getMissedDays()); // missed one day
    }
}
