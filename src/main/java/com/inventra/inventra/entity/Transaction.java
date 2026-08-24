package com.inventra.inventra.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // STOCK IN / STOCK OUT

    private Long productId;

    private int quantity;

    private LocalDateTime dateTime;
}