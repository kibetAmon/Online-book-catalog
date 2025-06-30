package com.amon.book_catalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthViewController {

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "auth/login"; // Loads templates/auth/login.html
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        return "auth/register"; // Loads templates/auth/register.html
    }
}
