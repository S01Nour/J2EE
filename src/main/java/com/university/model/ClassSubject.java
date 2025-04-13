package com.university.model;

import jakarta.persistence.*;

@Entity
@Table(name = "class_subject")
@IdClass(ClassSubjectId.class)
public class ClassSubject {
    // Remove @EmbeddedId and use @Id on both fields
    @Id
    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass classEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public SchoolClass getClassEntity() { return classEntity; }
    public void setClassEntity(SchoolClass classEntity) { this.classEntity = classEntity; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
}