package com.inventra.inventra.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecommendationService {

    public String generateRecommendations(Map<String, Object> data) {

        int stockIn = (int) data.get("totalStockIn");
        int stockOut = (int) data.get("totalStockOut");

        StringBuilder result = new StringBuilder();

        if(stockOut > stockIn) {
            result.append("⚠ High demand detected → Increase stock levels. ");
        }

        if(stockIn > stockOut * 2) {
            result.append("📉 Overstock detected → Reduce purchasing. ");
        }

        if(result.length() == 0) {
            result.append("✅ Stock levels are balanced.");
        }

        return result.toString();
    }
}
