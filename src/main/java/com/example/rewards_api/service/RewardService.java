package com.example.rewards_api.service;

import com.example.rewards_api.dto.RewardResponse;
import com.example.rewards_api.entity.Transaction;

import java.util.List;

public interface RewardService {
    RewardResponse getRewards(Long customerId);
    RewardResponse calculateRewards(List<Transaction> transactions);
}
