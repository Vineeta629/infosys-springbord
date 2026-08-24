package com.inventra.inventra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventra.inventra.entity.Transaction;
import com.inventra.inventra.repository.TransactionRepository;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionValidationService validationService;

    public void log(String type, Long productId, int quantity, String user) {

        String result = validationService.validateTransaction(type, productId, quantity, user);

        if(!result.equals("VALID")) {
            return;
        }

        Transaction tx = new Transaction();
        tx.setProductId(productId);
        tx.setQuantity(quantity);

        // ✅ FIXES

        tx.setType(type);                  // ✔
        tx.setDateTime(LocalDateTime.now()); // ✔

        transactionRepository.save(tx);
    }
}