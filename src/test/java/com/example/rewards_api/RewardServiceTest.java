package com.example.rewards_api;

import com.example.rewards_api.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RewardServiceTest {

    @Autowired
    private RewardService rewardService;

    @Test
    void testCalculatePoints() {
        assertEquals(90, rewardService.calculatePoints(120));
        assertEquals(25, rewardService.calculatePoints(75));
        assertEquals(0, rewardService.calculatePoints(40));
    }

    @Test
    void testZeroPoints() {
        assertEquals(0, rewardService.calculatePoints(50));
    }
}
