package com.example.rewards_api.controller;

import com.example.rewards_api.entity.Transaction;
import com.example.rewards_api.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void shouldReturnRewardsForCustomer() throws Exception {

        repository.save(Transaction.builder()
                .customerId(1L)
                .amount(120)
                .transactionDate(LocalDate.now())
                .build());

        mockMvc.perform(get("/api/rewards/1"))
                .andExpect(status().isOk());
    }
}
