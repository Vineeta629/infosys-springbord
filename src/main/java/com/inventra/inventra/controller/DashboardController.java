package com.inventra.inventra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventra.inventra.service.AnalyticsService;
import com.inventra.inventra.repository.ProductRepository;
import com.inventra.inventra.repository.AlertRepository;

import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AlertRepository alertRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        Map<String, Object> data = analyticsService.analyze();

        model.addAttribute("stockIn", data.get("totalStockIn"));
        model.addAttribute("stockOut", data.get("totalStockOut"));
        model.addAttribute("productUsage", data.get("productUsage"));

        // 🔥 cards data
        model.addAttribute("totalProducts", productRepository.count());
        model.addAttribute("activeAlerts", alertRepository.countByStatus("ACTIVE"));

        return "dashboard";
    }
}