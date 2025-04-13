package com.university.repository;

import com.university.model.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<SchoolClass, Long> {
    List<SchoolClass> findByNameContaining(String name);
}