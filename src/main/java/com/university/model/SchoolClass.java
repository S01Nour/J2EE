package com.university.model;
import com.university.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String semester;

    @ManyToMany(mappedBy = "classes")
    private List<Student> students;

    @ManyToMany
    @JoinTable(
            name = "class_subject",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL)
    private List<Schedule> schedules;
}