package com.university.repository;

import com.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    List<Student> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}