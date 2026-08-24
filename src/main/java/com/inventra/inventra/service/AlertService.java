package com.inventra.inventra.service;

import com.inventra.inventra.entity.Alert;
import com.inventra.inventra.entity.Product;
import com.inventra.inventra.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public void evaluateStockAlert(Product product) {

        String validation = validationService.validateProduct(product);

        if(!validation.equals("VALID")) {
            return;
        }

        if(product.getQuantity() <= 0) {
            createAlert(product, "OUT_OF_STOCK", "HIGH");
        }
        else if(product.getQuantity() < 5) {
            createAlert(product, "LOW_STOCK", "MEDIUM");
        }
    }
    public void createAlert(Product product, String type, String severity) {

        // ❌ Duplicate check
        boolean exists = alertRepository
                .existsByProductIdAndAlertTypeAndStatus(
                        product.getId(),
                        type,
                        "ACTIVE"
                );

        if(exists) return;

        Alert alert = new Alert();
        alert.setProductId(product.getId());
        alert.setAlertType(type);
        alert.setSeverity(severity);
        alert.setMessage(type + " for product: " + product.getName());
        alert.setCreatedDate(LocalDateTime.now());
        alert.setStatus("ACTIVE");

        alertRepository.save(alert);
        notificationService.sendAlert(alert);
    }
    @Autowired
    private AlertValidationService validationService;
    @Autowired
    private NotificationService notificationService;
}