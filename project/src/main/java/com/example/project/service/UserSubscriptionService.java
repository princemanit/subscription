package com.example.project.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionService {
	
	@Autowired JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getAllSubscriptions() {
        String sql = "SELECT * FROM subscription_master";
        return jdbcTemplate.queryForList(sql);
    }
	
	public Map<String, Object> getLatestSubscriptionByUserId(String userId) {
        String sql = "SELECT us.*, sm.title, sm.type, sm.price " +
                     "FROM user_subscription us " +
                     "JOIN subscription_master sm ON us.subscription_master_id = sm.id " +
                     "WHERE us.userId = ? " +
                     "ORDER BY us.start_date DESC " +
                     "LIMIT 1";

        return jdbcTemplate.queryForMap(sql, userId);
    }
	
	public boolean validateSubscription(String subscriptionMasterId) {
        String sql = "SELECT COUNT(*) FROM subscription_master WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, subscriptionMasterId) > 0;
    }

    public void buySubscription(String userId, String subscriptionMasterId) {
        String subscriptionType = getSubscriptionType(subscriptionMasterId);
        Date startDate = new Date();
        Date endDate = calculateEndDate(startDate, subscriptionType);

        String sql = "INSERT INTO user_subscription (id, userId, subscription_master_id, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID().toString(), userId, subscriptionMasterId, startDate, endDate);
    }

    private String getSubscriptionType(String subscriptionMasterId) {
        String sql = "SELECT type FROM subscription_master WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, subscriptionMasterId);
    }

    private Date calculateEndDate(Date startDate, String subscriptionType) {
        long duration = subscriptionType.equalsIgnoreCase("Monthly") ? 30L * 24 * 60 * 60 * 1000 : 365L * 24 * 60 * 60 * 1000; // in milliseconds
        return new Date(startDate.getTime() + duration);
    }

}
