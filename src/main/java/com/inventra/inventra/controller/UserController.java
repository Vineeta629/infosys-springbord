package com.inventra.inventra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.inventra.inventra.entity.User;
import com.inventra.inventra.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @GetMapping("/admin/test")
    public String adminTest() {
        return "Admin Access Granted";
    }

    @GetMapping("/staff/test")
    public String staffTest() {
        return "Staff Access Granted";
    }
    @GetMapping("/all")
    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/addTest")
    public String addTestUser() {
        User user = new User();
        user.setName("Vineeta");
        user.setEmail("vineeta@test.com");
        user.setPassword(passwordEncoder.encode("12345"));
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);
        return "User Added";
    }
    @GetMapping("/admin/categories")
    public String categories() {
        return "categories";
    }

}