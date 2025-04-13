package com.university.model;

import java.io.Serializable;
import java.util.Objects;

public class EnrollmentId implements Serializable {
    private Long student;
    private Long classEntity;

    // Default constructor
    public EnrollmentId() {}

    // Parameterized constructor
    public EnrollmentId(Long student, Long classEntity) {
        this.student = student;
        this.classEntity = classEntity;
    }

    // Getters and Setters
    public Long getStudent() { return student; }
    public void setStudent(Long student) { this.student = student; }
    public Long getClassEntity() { return classEntity; }
    public void setClassEntity(Long classEntity) { this.classEntity = classEntity; }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(student, that.student) &&
                Objects.equals(classEntity, that.classEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, classEntity);
    }
}