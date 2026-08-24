package com.inventra.inventra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventra.inventra.repository.TransactionRepository;
import com.inventra.inventra.entity.Transaction;

import java.util.*;

@Service
public class AnalyticsService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Map<String, Object> analyze() {

        List<Transaction> list = transactionRepository.findAll();

        int stockIn = 0;
        int stockOut = 0;

        Map<Long, Integer> productUsage = new HashMap<>();

        for(Transaction t : list) {

            String type = t.getType().trim().toUpperCase().replace(" ", "_");

            if("STOCK_IN".equals(type)) {
                stockIn += t.getQuantity();
            } else if("STOCK_OUT".equals(type)) {
                stockOut += t.getQuantity();
            }

            productUsage.put(
                    t.getProductId(),
                    productUsage.getOrDefault(t.getProductId(), 0) + t.getQuantity()
            );
        }
        Map<String, Object> result = new HashMap<>();

        result.put("totalStockIn", stockIn);
        result.put("totalStockOut", stockOut);
        result.put("productUsage", productUsage);

        return result;
    }
}




