package com.example.rewards_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyReward {
    private String month;
    private int points;
}
