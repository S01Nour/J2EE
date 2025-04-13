package com.university.repository;

import com.university.model.ClassSubject;
import com.university.model.ClassSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassSubjectRepository extends JpaRepository<ClassSubject, ClassSubjectId> {
    // Custom queries
    List<ClassSubject> findByClassEntityClassId(Long classId);
    List<ClassSubject> findBySubjectSubjectId(Long subjectId);

    // Example of finding by composite key
    default ClassSubject findByClassAndSubject(Long classId, Long subjectId) {
        ClassSubjectId id = new ClassSubjectId(classId, subjectId);
        return findById(id).orElse(null);
    }

    Optional<ClassSubject> findById(ClassSubjectId id);
}