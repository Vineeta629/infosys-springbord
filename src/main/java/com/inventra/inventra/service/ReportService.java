


package com.inventra.inventra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventra.inventra.entity.Report;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private AnalyticsService analyticsService;

    public Report generateReport() {

        // 🔥 Step 1: Get analytics data
        Map<String, Object> data = analyticsService.analyze();

        // 🔥 Step 2: Generate recommendation
        String recommendation = recommendationService.generateRecommendations(data);

        // 🔥 Step 3: Create report object
        Report report = new Report();

        // 🔥 Step 4: Extract values
        int stockIn = (Integer) data.get("totalStockIn");
        int stockOut = (Integer) data.get("totalStockOut");

        // 🔥 Step 5: Fill report
        report.setReportType("Inventory Analytics");
        report.setGeneratedDate(LocalDateTime.now());

        report.setDataSummary(
                "Stock In: " + stockIn + " | Stock Out: " + stockOut
        );

        report.setRecommendations(recommendation);

        return report;
    }
}