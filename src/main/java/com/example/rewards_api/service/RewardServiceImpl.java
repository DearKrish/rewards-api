package com.example.rewards_api.service;

import com.example.rewards_api.dto.MonthlyReward;
import com.example.rewards_api.dto.RewardResponse;
import com.example.rewards_api.entity.Transaction;
import com.example.rewards_api.exception.ResourceNotFoundException;
import com.example.rewards_api.repository.TransactionRepository;
import com.example.rewards_api.util.RewardCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public RewardResponse calculateRewards(List<Transaction> transactions) {

        if (transactions == null || transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found");
        }

        int totalPoints = transactions.stream()
                .mapToInt(tx -> RewardCalculator.calculate(tx.getAmount()))
                .sum();

        return new RewardResponse(totalPoints);
    }

    @Override
    public RewardResponse getRewards(Long customerId) {

        List<Transaction> transactions = repository.findByCustomerId(customerId);

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found");
        }

        Map<String, Integer> monthlyPoints = new HashMap<>();

        for (Transaction tx : transactions) {

            String month = tx.getTransactionDate().getMonth().toString();

            int points = RewardCalculator.calculate(tx.getAmount());

            monthlyPoints.put(month,
                    monthlyPoints.getOrDefault(month, 0) + points);
        }

        List<MonthlyReward> monthlyList = monthlyPoints.entrySet()
                .stream()
                .map(e -> new MonthlyReward(e.getKey(), e.getValue()))
                .toList();

        int total = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();

        return new RewardResponse(customerId, monthlyList, total);
    }
}
