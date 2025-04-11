package com.university.repository;

import com.university.model.Class;
import com.university.model.Enrollment;
import com.university.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Enrollment> {
    boolean existsByStudentAndClassEntity(Student student, Class classEntity);
}
