package com.university.service;

import com.university.model.SchoolClass;
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

    public List<Schedule> findByClass(SchoolClass classEntity) {
        return scheduleRepository.findByClassEntity_ClassId(classEntity.getClassId());
    }

    public List<Schedule> findByClasses(List<SchoolClass> classes) {
        return scheduleRepository.findSchedulesByStudentId(classes.get(0).getClassId());
    }
}