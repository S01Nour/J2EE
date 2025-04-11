package com.university.controller;

import com.university.model.Class;
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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentService.findByEmail(email);
        List<Class> classes = student.getClasses();
        List<Schedule> schedules = scheduleService.findByClasses(classes);
        model.addAttribute("schedules", schedules);
        return "student/schedule";
    }

    @GetMapping("/enroll")
    public String enrollForm(Model model) {
        model.addAttribute("enrollment", new Enrollment());
        model.addAttribute("classes", classService.findAll());
        return "student/enroll";
    }

    @PostMapping("/enroll/save")
    public String enroll(@ModelAttribute("enrollment") Enrollment enrollment, Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentService.findByEmail(email);
        Class classEntity = enrollment.getClassEntity();

        if (enrollmentService.isEnrolled(student, classEntity)) {
            model.addAttribute("error", "You are already enrolled in this class");
            model.addAttribute("classes", classService.findAll());
            return "student/enroll";
        }

        enrollment.setStudent(student);
        enrollment.setEnrollmentDate(new Date());
        enrollmentService.save(enrollment);
        return "redirect:/student/schedule";
    }
}