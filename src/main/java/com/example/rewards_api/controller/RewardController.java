package com.example.rewards_api.controller;

import com.example.rewards_api.model.RewardResponse;
import com.example.rewards_api.model.Transaction;
import com.example.rewards_api.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public Map<Long, RewardResponse> getRewards() {
        return rewardService.calculateRewards(getMockData());
    }

    private List<Transaction> getMockData() {

        return List.of(
                new Transaction(1L, 101L, 120, LocalDate.now().minusMonths(1)),
                new Transaction(2L, 101L, 75, LocalDate.now().minusMonths(2)),
                new Transaction(3L, 102L, 200, LocalDate.now()),
                new Transaction(4L, 102L, 50, LocalDate.now().minusMonths(1))
        );
    }
}
