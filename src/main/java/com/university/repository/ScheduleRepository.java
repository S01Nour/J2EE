package com.university.repository;

import com.university.model.Class;
import com.university.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByClassEntity(Class classEntity);
    List<Schedule> findByClassEntityIn(List<Class> classes);
}