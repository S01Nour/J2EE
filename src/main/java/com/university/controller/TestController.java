package com.university.controller;

import com.university.model.Student;
import com.university.repository.StudentRepository;
import com.university.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/encode-password")
    public String encodePassword(@RequestParam String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @GetMapping("/find-user")
    public String findUser(@RequestParam String email) {
        try {
            UserDetails user = userDetailsService.loadUserByUsername(email);
            return "Found user: " + user.getUsername() +
                    ", Authorities: " + user.getAuthorities() +
                    ", Password (first 10 chars): " + user.getPassword().substring(0, 10) + "...";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/check-password")
    public String checkPassword(@RequestParam String rawPassword, @RequestParam String email) {
        try {
            UserDetails user = userDetailsService.loadUserByUsername(email);
            boolean matches = passwordEncoder.matches(rawPassword, user.getPassword());
            return "Password matches: " + matches;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/create-user")
    public String createUser(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String role) {
        try {
            Student student = new Student();
            student.setEmail(email);
            student.setFirstName("Test");
            student.setLastName("User");
            student.setPassword(passwordEncoder.encode(password));
            student.setRole(role);
            studentRepository.save(student);
            return "Created user with email: " + email;
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }
}