package com.university.service;

import com.university.model.Student;
import com.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Attempting to load user with email: " + email);

        Student student = studentRepository.findByEmail(email);
        if (student == null) {
            System.out.println("User not found with email: " + email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        System.out.println("Found user: " + student.getUsername() + " with role: " + student.getRole());
        return student;
    }
}



//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Student student = studentRepository.findByEmail(email);
//        if (student == null) {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//        return student;
//    }
//}

