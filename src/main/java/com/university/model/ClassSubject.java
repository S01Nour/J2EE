package com.university.model;

import javax.persistence.*;

@Entity
@Table(name = "class_subject")
public class ClassSubject {

    @Id
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    // Getters and Setters
    public Class getClassEntity() { return classEntity; }
    public void setClassEntity(Class classEntity) { this.classEntity = classEntity; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
}