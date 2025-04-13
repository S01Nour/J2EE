package com.university.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import jakarta.persistence.*;
@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int credits;

    @ManyToMany(mappedBy = "subjects")
    private List<SchoolClass> classes;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Schedule> schedules;
}