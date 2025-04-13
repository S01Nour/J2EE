package com.university.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "enrollment")
@IdClass(EnrollmentId.class) // Add this annotation
public class Enrollment {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass classEntity;

    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;

    // Getters and Setters remain the same
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public SchoolClass getClassEntity() { return classEntity; }
    public void setClassEntity(SchoolClass classEntity) { this.classEntity = classEntity; }
    public Date getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(Date enrollmentDate) { this.enrollmentDate = enrollmentDate; }
}