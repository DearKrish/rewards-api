package com.example.rewards_api.service;

import com.example.rewards_api.model.RewardResponse;
import com.example.rewards_api.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardService {

    public int calculatePoints(double amount) {

        if (amount > 100) {
            return (int)((amount - 100) * 2 + 50);
        } else if (amount > 50) {
            return (int)(amount - 50);
        }
        return 0;
    }

    public Map<Long, RewardResponse> calculateRewards(List<Transaction> transactions) {

        Map<Long, RewardResponse> result = new HashMap<>();

        for (Transaction txn : transactions) {

            int points = calculatePoints(txn.getAmount());
            String month = txn.getDate().getMonth().toString();

            result.putIfAbsent(txn.getCustomerId(), new RewardResponse());

            RewardResponse response = result.get(txn.getCustomerId());
            response.setCustomerId(txn.getCustomerId());

            response.getMonthlyPoints()
                    .merge(month, points, Integer::sum);

            response.setTotalPoints(response.getTotalPoints() + points);
        }

        return result;
    }
}
