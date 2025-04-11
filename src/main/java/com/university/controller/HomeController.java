package com.university.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (role.contains("ROLE_ADMIN")) {
            return "redirect:/admin/students";
        } else {
            return "redirect:/student/schedule";
        }
    }
}