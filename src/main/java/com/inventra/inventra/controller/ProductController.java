package com.inventra.inventra.controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.inventra.inventra.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.inventra.inventra.repository.ProductRepository;
import com.inventra.inventra.entity.Product;

@Controller
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @GetMapping("/admin/products")
    public String viewProducts(Model model) {

        model.addAttribute("products", productRepository.findAll());

        return "products";
    }
    @GetMapping("/admin/products/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "add-product";
    }



    @PostMapping("/admin/products/save")
    public String saveProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Product product = productRepository.findById(id).orElseThrow();

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());

        return "edit-product";
    }


    @PostMapping("/admin/products/update")
    public String updateProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }

}