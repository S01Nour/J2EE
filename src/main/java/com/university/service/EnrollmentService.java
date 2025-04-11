package com.university.service;

import com.university.model.Class;
import com.university.model.Enrollment;
import com.university.model.Student;
import com.university.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public boolean isEnrolled(Student student, Class classEntity) {
        return enrollmentRepository.existsByStudentAndClassEntity(student, classEntity);
    }

    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }
}