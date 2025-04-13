package com.university.repository;

import com.university.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByClassEntity_ClassId(Long classId);

    @Query("SELECT s FROM Schedule s WHERE s.classEntity.classId IN (SELECT c.classId FROM Student st JOIN st.classes c WHERE st.studentId = ?1)")
    List<Schedule> findSchedulesByStudentId(Long studentId);

    @Query("SELECT COUNT(*) FROM Schedule s WHERE s.classEntity.classId = ?1 AND s.day = ?2 AND " +
            "((s.startTime <= ?3 AND s.endTime >= ?3) OR (s.startTime <= ?4 AND s.endTime >= ?4))")
    int countConflicts(Long classId, String day, String startTime, String endTime);
}