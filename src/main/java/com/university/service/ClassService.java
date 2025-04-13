package com.university.service;

import com.university.model.SchoolClass;
import com.university.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    public SchoolClass save(SchoolClass classEntity) {
        return classRepository.save(classEntity);
    }

    public SchoolClass findById(Long id) {
        return classRepository.findById(id).orElse(null);
    }

    public List<SchoolClass> findAll() {
        return classRepository.findAll();
    }

    public void deleteById(Long id) {
        classRepository.deleteById(id);
    }

    public List<SchoolClass> search(String keyword) {
        return classRepository.findByNameContaining(keyword);
    }
}