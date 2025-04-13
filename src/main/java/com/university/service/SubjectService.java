package com.university.service;

import com.university.model.Subject;
import com.university.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    public List<Subject> search(String keyword) {
        return subjectRepository.findByNameContaining(keyword);
    }
}