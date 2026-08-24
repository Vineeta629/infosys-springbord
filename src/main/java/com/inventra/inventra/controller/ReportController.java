package com.inventra.inventra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventra.inventra.service.ReportService;
import com.inventra.inventra.service.AnalyticsService;

import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/admin/report")
    public String viewReport(Model model) {

        var report = reportService.generateReport();
        model.addAttribute("report", report);

        // 🔥 analytics data
        Map<String, Object> data = analyticsService.analyze();
        model.addAttribute("stockIn", data.get("totalStockIn"));
        model.addAttribute("stockOut", data.get("totalStockOut"));

        return "report";
    }
}