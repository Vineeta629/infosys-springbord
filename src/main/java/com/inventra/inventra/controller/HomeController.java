

package com.inventra.inventra.controller;

import com.inventra.inventra.repository.ProductRepository;
import com.inventra.inventra.repository.CategoryRepository;
import com.inventra.inventra.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.inventra.inventra.entity.Product;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/")
    public String homePage(Model model){

        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());

        return "home";
    }


    @GetMapping("/category/{id}")
    public String productsByCategory(@PathVariable Long id, Model model){

        Category category = categoryRepository.findById(id).orElse(null);

        model.addAttribute("products", productRepository.findByCategory(category));
        model.addAttribute("categories", categoryRepository.findAll());

        return "home";
    }
    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model){

        Product product = productRepository.findById(id).orElse(null);

        model.addAttribute("product", product);

        return "product-detail";
    }


}
