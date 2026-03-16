
package com.inventra.inventra.controller;
import java.util.List;
import com.inventra.inventra.entity.OrderItem;

import com.inventra.inventra.entity.CartItem;
import com.inventra.inventra.entity.Product;
import com.inventra.inventra.repository.CartRepository;
import com.inventra.inventra.repository.ProductRepository;
import com.inventra.inventra.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;



    @GetMapping("/cart")
    public String viewCart(Model model){

        List<CartItem> items = cartRepository.findAll();

        double total = 0;

        for(CartItem item : items){
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        model.addAttribute("cartItems", items);
        model.addAttribute("totalPrice", total);

        return "cart";
    }


    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id){

        Product product = productRepository.findById(id).orElse(null);

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(1);

        cartRepository.save(item);

        return "redirect:/cart";
    }


    @GetMapping("/cart/remove/{id}")
    public String removeItem(@PathVariable Long id){

        cartRepository.deleteById(id);

        return "redirect:/cart";
    }
    @GetMapping("/checkout")
    public String checkout(Model model){

        List<CartItem> items = cartRepository.findAll();

        for(CartItem item : items){

            OrderItem order = new OrderItem();

            order.setProductName(item.getProduct().getName());
            order.setPrice(item.getProduct().getPrice());
            order.setQuantity(item.getQuantity());

            orderRepository.save(order);
        }

        cartRepository.deleteAll();

        model.addAttribute("message", "Order placed successfully!");

        return "checkout-success";
    }


}

