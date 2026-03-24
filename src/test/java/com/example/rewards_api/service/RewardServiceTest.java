package com.example.rewards_api.service;

import com.example.rewards_api.dto.RewardResponse;
import com.example.rewards_api.entity.Transaction;
import com.example.rewards_api.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RewardServiceTest {

    private RewardServiceImpl rewardService;

    @BeforeEach
    void setup() {
        rewardService = new RewardServiceImpl();
    }

    @Test
    void shouldCalculateRewardsForMultipleTransactions() {

        List<Transaction> transactions = List.of(
                Transaction.builder().amount(120).transactionDate(LocalDate.of(2024, 1, 10)).build(),
                Transaction.builder().amount(80).transactionDate(LocalDate.of(2024, 1, 15)).build(),
                Transaction.builder().amount(60).transactionDate(LocalDate.of(2024, 2, 10)).build()
        );

        RewardResponse response = rewardService.calculateRewards(transactions);

        assertNotNull(response);
        assertEquals(130, response.getTotalPoints());
        // 120 → 90, 80 → 30, 60 → 10 = 130
    }

    @Test
    void shouldReturnZeroPointsForLowAmount() {

        List<Transaction> transactions = List.of(
                Transaction.builder().amount(40).transactionDate(LocalDate.now()).build()
        );

        RewardResponse response = rewardService.calculateRewards(transactions);

        assertEquals(0, response.getTotalPoints());
    }

    @Test
    void shouldThrowExceptionWhenTransactionsEmpty() {

        assertThrows(ResourceNotFoundException.class,
                () -> rewardService.calculateRewards(List.of()));
    }

    @Test
    void shouldHandleMultipleCustomersSeparately() {

        List<Transaction> transactions = List.of(
                Transaction.builder()
                        .customerId(1L)
                        .amount(120)
                        .transactionDate(LocalDate.now())
                        .build(),

                Transaction.builder()
                        .customerId(2L)
                        .amount(80)
                        .transactionDate(LocalDate.now())
                        .build()
        );

        RewardResponse response = rewardService.calculateRewards(transactions);

        assertNotNull(response);
    }
}
