package com.inventra.inventra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.inventra.inventra.entity.Transaction;
import com.inventra.inventra.repository.TransactionRepository;

@Service
public class TransactionFilteringService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getFilteredTransactions(
            String type,
            Long productId) {

        List<Transaction> list = transactionRepository.findAll();

        // ✅ TYPE FILTER FIXED (extra safe)
        if(type != null && !type.isEmpty()) {
            list = list.stream()
                    .filter(t -> {
                        if(t.getType() == null) return false;

                        // 🔥 normalize both sides
                        String dbType = t.getType().trim().toUpperCase().replace(" ", "_");
                        String reqType = type.trim().toUpperCase();

                        return dbType.equals(reqType);
                    })
                    .toList();
        }

        // ✅ PRODUCT FILTER
        if(productId != null) {
            list = list.stream()
                    .filter(t -> t.getProductId() != null && t.getProductId().equals(productId))
                    .toList();
        }

        return list;
    }
}