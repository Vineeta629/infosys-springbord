

package com.inventra.inventra.controller;

import com.inventra.inventra.repository.ProductRepository;
import com.inventra.inventra.repository.CategoryRepository;
import com.inventra.inventra.repository.CartRepository;

import com.inventra.inventra.entity.Category;
import com.inventra.inventra.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CartRepository cartRepository;


    @GetMapping("/")
    public String homePage(Model model){

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cartCount", cartRepository.count());

        return "home";
    }


    @GetMapping("/category/{id}")
    public String productsByCategory(@PathVariable Long id, Model model){

        Category category = categoryRepository.findById(id).orElse(null);

        model.addAttribute("products", productRepository.findByCategory(category));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cartCount", cartRepository.count());

        return "home";
    }


    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model){

        Product product = productRepository.findById(id).orElse(null);

        model.addAttribute("product", product);
        model.addAttribute("cartCount", cartRepository.count());

        return "product-detail";
    }

}

