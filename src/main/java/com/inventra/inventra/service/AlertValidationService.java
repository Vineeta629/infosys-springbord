package com.inventra.inventra.service;

import org.springframework.stereotype.Service;
import com.inventra.inventra.entity.Product;

@Service
public class AlertValidationService {

    public String validateProduct(Product product) {

        if(product == null) {
            return "Invalid product";
        }

        if(product.getQuantity() < 0) {
            return "Invalid quantity";
        }

        return "VALID";
    }
}
