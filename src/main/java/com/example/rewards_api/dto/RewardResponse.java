package com.example.rewards_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardResponse {

    private Long customerId;
    private List<MonthlyReward> monthlyRewards;
    private int totalPoints;

    public RewardResponse(int totalPoints) {
        this.totalPoints=totalPoints;
    }
}