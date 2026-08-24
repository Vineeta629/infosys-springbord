package com.inventra.inventra.controller;
import com.inventra.inventra.service.EmailService;
import com.inventra.inventra.entity.User;
import com.inventra.inventra.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;


    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {

        if(userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        user.setRole("ROLE_USER");

        // 🔥 MUST
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "login";
    }






    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    /*@PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model){

        User user = userRepository.findByEmail(email);

        if(user != null && user.getPassword().equals(password)){
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

    }*/
    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email, Model model) {

        User user = userRepository.findByEmail(email);

        if(user == null) {
            model.addAttribute("error", "Email not registered");
            return "forgot-password";
        }

        String otp = String.valueOf((int)(Math.random() * 9000) + 1000);

        user.setOtp(otp);
        user.setOtpExpiry(java.time.LocalDateTime.now().plusMinutes(5));

        userRepository.save(user);

        emailService.sendOtp(email, otp);

        model.addAttribute("message", "OTP sent to your email");
        model.addAttribute("email", email);
        System.out.println("Send OTP API hit");

        return "reset-password";
    }
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String otp,
                                @RequestParam String newPassword,
                                Model model) {

        User user = userRepository.findByEmail(email);

        if(user == null) {
            model.addAttribute("error", "Invalid email");
            return "forgot-password";
        }

        if(user.getOtp() == null || !user.getOtp().equals(otp)) {
            model.addAttribute("error", "Invalid OTP");
            return "forgot-password";
        }

        if(user.getOtpExpiry().isBefore(java.time.LocalDateTime.now())) {
            model.addAttribute("error", "OTP expired");
            return "forgot-password";
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        user.setOtp(null);
        user.setOtpExpiry(null);

        userRepository.save(user);
        model.addAttribute("email", email);

        model.addAttribute("message", "Password reset successful");
        return "login";
    }



}