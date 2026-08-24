package com.inventra.inventra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventra.inventra.entity.Product;
import com.inventra.inventra.repository.ProductRepository;
import com.inventra.inventra.service.AlertService;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductValidationService validationService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AlertService alertService;


    public String stockIn(Long id, int quantity) {

        Product product = productRepository.findById(id).orElse(null);

        if(product == null) return "Product not found";
        if(quantity <= 0) return "Invalid quantity";

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        transactionService.log("STOCK_IN", id, quantity,"ADMIN");

// ✅ ALERT CALL YAHAN HONA CHAHIYE
        alertService.evaluateStockAlert(product);

        return "Stock updated";
    }

    public String stockOut(Long id, int quantity) {

        Product product = productRepository.findById(id).orElse(null);

        String validation = validationService.validateStockOut(product, quantity);

        if(!validation.equals("VALID")) {
            return validation;
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        transactionService.log("STOCK_OUT", id, quantity,"ADMIN");

// ✅ ALERT CALL
        alertService.evaluateStockAlert(product);

        return "Stock updated";
    }
}