
package com.inventra.inventra.controller;
import com.inventra.inventra.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.inventra.inventra.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.inventra.inventra.repository.ProductRepository;
import com.inventra.inventra.entity.Product;
import com.inventra.inventra.service.ProductService;
import com.inventra.inventra.service.AnalyticsService;

import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/admin/products")
    public String viewProducts(Model model) {

        model.addAttribute("products", productRepository.findAll());

        Map<String, Object> data = analyticsService.analyze();
        model.addAttribute("stockIn", data.get("totalStockIn"));
        model.addAttribute("stockOut", data.get("totalStockOut"));

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

    // ✅ STOCK IN PAGE
    @GetMapping("/admin/products/stock-in/{id}")
    public String stockInPage(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "stock-in";
    }

    // ✅ STOCK IN SAVE
    @Autowired
    private ProductService productService;


    @PostMapping("/admin/products/stock-in")
    public String stockIn(@RequestParam Long id,
                          @RequestParam int quantity,
                          Model model) {

        String result = productService.stockIn(id, quantity);

        if(!result.equals("Stock updated")) {
            Product product = productRepository.findById(id).get();
            model.addAttribute("product", product);
            model.addAttribute("error", result);
            return "stock-in";
        }

        return "redirect:/admin/products";
    }
    // ✅ STOCK OUT PAGE
    @GetMapping("/admin/products/stock-out/{id}")
    public String stockOutPage(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "stock-out";
    }

    // ✅ STOCK OUT SAVE
    @PostMapping("/admin/products/stock-out")
    public String stockOut(@RequestParam Long id,
                           @RequestParam int quantity,
                           Model model) {

        String result = productService.stockOut(id, quantity);

        if(!result.equals("Stock updated")) {
            Product product = productRepository.findById(id).get();
            model.addAttribute("product", product);
            model.addAttribute("error", result);
            return "stock-out";
        }

        return "redirect:/admin/products";
    }
    @PostMapping("/admin/products/update")
    public String updateProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }
    @Autowired
    private TransactionRepository transactionRepository;


}