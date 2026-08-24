package com.inventra.inventra.controller;
import com.inventra.inventra.repository.CategoryRepository;
import com.inventra.inventra.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventra.inventra.repository.ProductRepository;

import com.inventra.inventra.repository.UserRepository;



@Controller
public class AdminController {

    /*@GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }*/

    @GetMapping("/admin/home")
    public String adminDashboard(Model model) {

        long totalProducts = productRepository.count();
        long totalCategories = categoryRepository.count();
        long totalUsers = userRepository.count();

        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("totalCategories", totalCategories);
        model.addAttribute("totalUsers", totalUsers);

        model.addAttribute("products", productRepository.findAll());

        return "admin-dashboard";
    }
    @GetMapping("/admin/test")
    public String adminTest() {
        return "Admin access successful!";
    }

    @GetMapping("/admin/users")
    public String usersPage() {
        return "users";
    }



    @GetMapping("/admin/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping("/admin/categories/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;
}
