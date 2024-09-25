package com.example.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.service.UserSubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class UserSubscriptionController {
	
	@Autowired UserSubscriptionService subscriptionService;
	
	@GetMapping("/fetchAll")
    public ResponseEntity<List<Map<String, Object>>> getAllSubscriptions() {
        List<Map<String, Object>> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }
	
	@GetMapping("/latest/{userId}")
    public ResponseEntity<Map<String, Object>> getLatestSubscription(@PathVariable String userId) {
        Map<String, Object> latestSubscription = subscriptionService.getLatestSubscriptionByUserId(userId);
        return latestSubscription != null ? ResponseEntity.ok(latestSubscription) : ResponseEntity.notFound().build();
    }
	
	@PostMapping("/buy")
    public ResponseEntity<String> buySubscription(@RequestParam String userId, @RequestParam String subscriptionMasterId) {
        if (!subscriptionService.validateSubscription(subscriptionMasterId)) {
            return ResponseEntity.badRequest().body("Invalid subscription master ID.");
        }

        subscriptionService.buySubscription(userId, subscriptionMasterId);
        return ResponseEntity.ok("Subscription purchased successfully.");
    }	

}
