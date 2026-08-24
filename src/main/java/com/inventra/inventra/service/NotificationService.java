package com.inventra.inventra.service;

import com.inventra.inventra.entity.Alert;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendAlert(Alert alert) {

        // 🔥 Console Notification
        System.out.println("🚨 ALERT: " + alert.getMessage());

        // 🔥 Severity based message
        if(alert.getSeverity().equals("HIGH")) {
            System.out.println("🔥 HIGH PRIORITY ALERT !!!");
        }
    }
}