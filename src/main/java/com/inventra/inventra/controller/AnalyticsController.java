package com.inventra.inventra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventra.inventra.service.AnalyticsService;

import java.util.Map;


    @Controller
    public class AnalyticsController {

        @Autowired
        private AnalyticsService analyticsService;

        @GetMapping("/admin/analytics")
        public String analytics(Model model) {

            Map<String, Object> data = analyticsService.analyze();

            model.addAttribute("stockIn", data.get("totalStockIn"));
            model.addAttribute("stockOut", data.get("totalStockOut"));
            model.addAttribute("productUsage", data.get("productUsage"));

            return "analytics";
        }
    }

