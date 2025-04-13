package com.university.model;

import java.io.Serializable;
import java.util.Objects;

public class ClassSubjectId implements Serializable {
    private Long classEntity;  // This must match exactly with the field name
    private Long subject;     // This must match exactly with the field name

    // Default constructor
    public ClassSubjectId() {}

    // Parameterized constructor - names must match fields
    public ClassSubjectId(Long classEntity, Long subject) {
        this.classEntity = classEntity;
        this.subject = subject;
    }

    // Getters and Setters - must match field names
    public Long getClassEntity() { return classEntity; }
    public void setClassEntity(Long classEntity) { this.classEntity = classEntity; }
    public Long getSubject() { return subject; }
    public void setSubject(Long subject) { this.subject = subject; }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassSubjectId that = (ClassSubjectId) o;
        return Objects.equals(classEntity, that.classEntity) &&
                Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classEntity, subject);
    }
}