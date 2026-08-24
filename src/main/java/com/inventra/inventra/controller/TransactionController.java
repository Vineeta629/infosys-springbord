package com.inventra.inventra.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.inventra.inventra.service.TransactionFilteringService;
@Controller
public class TransactionController {

    @Autowired
    private TransactionFilteringService filteringService;

    @GetMapping("/admin/transactions")
    public String viewTransactions(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Long productId,
            Model model) {

        model.addAttribute("transactions",
                filteringService.getFilteredTransactions(type, productId));
        System.out.println("TYPE = " + type);
        System.out.println("PRODUCT ID = " + productId);

        return "transactions";
    }
}