package com.inventra.inventra.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class chartService {

    public Map<String, Object> prepareChartData(Map<String, Object> analytics) {

        Map<String, Object> chart = new HashMap<>();

        chart.put("stockIn", analytics.get("totalStockIn"));
        chart.put("stockOut", analytics.get("totalStockOut"));

        return chart;
    }
}