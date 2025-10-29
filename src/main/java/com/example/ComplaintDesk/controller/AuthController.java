package com.example.ComplaintDesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.ComplaintDesk.model.User;
import com.example.ComplaintDesk.service.UserService;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, 
                          @RequestParam String email, 
                          @RequestParam String password,
                          @RequestParam String role,
                          Model model) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setCreatedAt(LocalDate.now());
        userService.createUser(user);
        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, 
                       @RequestParam String password,
                       Model model) {
        // Simple authentication - find user by email
        List<User> allUsers = userService.getAllUsers();
        User foundUser = allUsers.stream()
            .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
            .findFirst()
            .orElse(null);
        
        if (foundUser != null) {
            model.addAttribute("user", foundUser);
            return "redirect:/complaints";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}

