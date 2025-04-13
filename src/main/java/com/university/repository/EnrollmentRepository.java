package com.university.repository;

import com.university.model.EnrollmentId;
import com.university.model.SchoolClass;
import com.university.model.Student;
import com.university.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {
    boolean existsByStudentAndClassEntity(Student student, SchoolClass classEntity);
//    List<Enrollment> findByStudentId(Long studentId);
//    List<Enrollment> findByClassEntityClassId(Long classId);
}
