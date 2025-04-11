package com.university.service;

import com.university.model.Class;
import com.university.model.Schedule;
import com.university.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    public Schedule save(Schedule schedule) {
        // Add conflict check logic here in the future
        return scheduleRepository.save(schedule);
    }

    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }

    public List<Schedule> findByClass(Class classEntity) {
        return scheduleRepository.findByClassEntity(classEntity);
    }

    public List<Schedule> findByClasses(List<Class> classes) {
        return scheduleRepository.findByClassEntityIn(classes);
    }
}