
package com.inventra.inventra.entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.*;
        import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
   // private String category;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private int quantity;
    private double price;
}