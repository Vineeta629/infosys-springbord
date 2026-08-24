package com.inventra.inventra.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    private Long productId;

    private String alertType; // LOW_STOCK, OUT_OF_STOCK

    private String severity; // HIGH, MEDIUM, LOW

    private String message;

    private LocalDateTime createdDate;

    private String status; // ACTIVE, RESOLVED
}
