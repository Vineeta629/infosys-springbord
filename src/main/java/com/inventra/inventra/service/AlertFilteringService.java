package com.inventra.inventra.service;

import com.inventra.inventra.entity.Alert;
import com.inventra.inventra.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertFilteringService {

    @Autowired
    private AlertRepository alertRepository;

    public List<Alert> getFilteredAlerts(String type, String severity, String status) {

        List<Alert> alerts = alertRepository.findAll();

        // 🔍 Filter by Type
        if(type != null && !type.isEmpty()) {
            alerts = alerts.stream()
                    .filter(a -> a.getAlertType().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        }

        // 🔍 Filter by Severity
        if(severity != null && !severity.isEmpty()) {
            alerts = alerts.stream()
                    .filter(a -> a.getSeverity().equalsIgnoreCase(severity))
                    .collect(Collectors.toList());
        }

        // 🔍 Filter by Status
        if(status != null && !status.isEmpty()) {
            alerts = alerts.stream()
                    .filter(a -> a.getStatus().equalsIgnoreCase(status))
                    .collect(Collectors.toList());
        }

        return alerts;
    }
}
