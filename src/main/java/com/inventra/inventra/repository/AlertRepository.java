package com.inventra.inventra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventra.inventra.entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    boolean existsByProductIdAndAlertTypeAndStatus(Long productId, String alertType, String status);
    long countByStatus(String status);
}