package com.inventra.inventra.service;

import org.springframework.stereotype.Service;
import com.inventra.inventra.entity.Product;

@Service
public class ProductValidationService {

    public String validateStockOut(Product product, int quantity) {

        if(product == null) return "Product not found";
        if(quantity <= 0) return "Invalid quantity";
        if(product.getQuantity() < quantity) return "Insufficient stock";

        return "VALID";
    }
}