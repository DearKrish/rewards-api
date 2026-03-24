package com.example.rewards_api.controller;

import com.example.rewards_api.dto.RewardResponse;
import com.example.rewards_api.entity.Transaction;
import com.example.rewards_api.service.RewardService;
import com.example.rewards_api.service.RewardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService service;

    @GetMapping("/{customerId}")
    public ResponseEntity<RewardResponse> getRewards(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getRewards(customerId));
    }
}
