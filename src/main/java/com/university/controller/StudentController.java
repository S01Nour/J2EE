package com.university.controller;

import com.university.model.SchoolClass;
import com.university.model.Enrollment;
import com.university.model.Schedule;
import com.university.model.Student;
import com.university.service.ClassService;
import com.university.service.EnrollmentService;
import com.university.service.ScheduleService;
import com.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/student")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/schedule")
    public String viewSchedule(Model model) {
        try {
            // Get current logged-in student
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Student student = studentService.findByEmail(email);

            if (student == null) {
                model.addAttribute("error", "Student not found");
                return "error/404";
            }

            // Get student's enrolled classes
            List<SchoolClass> classes = student.getClasses();

            // Handle case where student has no classes
            if (classes == null || classes.isEmpty()) {
                model.addAttribute("schedules", List.of());
                model.addAttribute("message", "You are not enrolled in any classes.");
                return "student/schedule";
            }

            // Get schedules for classes
            List<Schedule> schedules = scheduleService.findByClasses(classes);
            model.addAttribute("schedules", schedules);
            return "student/schedule";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to load schedule: " + e.getMessage());
            return "error/500";
        }
    }

    @GetMapping("/enroll")
    public String enrollForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("classes", classService.findAll());
        return "student/enroll";
    }

    @PostMapping("/enroll/save")
    public String enroll(@ModelAttribute("enrollment") Enrollment enrollment, Model model) {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Student student = studentService.findByEmail(email);
            SchoolClass classEntity = enrollment.getClassEntity();

            if (classEntity == null) {
                model.addAttribute("error", "Please select a valid class");
                model.addAttribute("classes", classService.findAll());
                return "student/enroll";
            }

            if (enrollmentService.isEnrolled(student, classEntity)) {
                model.addAttribute("error", "You are already enrolled in this class");
                model.addAttribute("classes", classService.findAll());
                return "student/enroll";
            }

            enrollment.setStudent(student);
            enrollment.setEnrollmentDate(new Date());
            enrollmentService.save(enrollment);
            return "redirect:/student/schedule";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to enroll: " + e.getMessage());
            model.addAttribute("classes", classService.findAll());
            return "student/enroll";
        }
    }
}