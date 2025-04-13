package com.university.controller;

import com.university.model.SchoolClass;
import com.university.model.Student;
import com.university.model.Subject;
import com.university.model.Payment;
import com.university.model.Schedule;
import com.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.university.service.ClassService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ScheduleService scheduleService;

    // Students
    @GetMapping("/students")
    public String listStudents(Model model, @RequestParam(required = false) String keyword) {
        List<Student> students = keyword == null ? studentService.findAll() : studentService.search(keyword);
        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);
        return "admin/students";
    }

    @GetMapping("/students/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "admin/student-form";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "admin/student-form";
    }

    @PostMapping("/students/save")
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/student-form";
        }
        studentService.save(student);
        return "redirect:/admin/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/admin/students";
    }

    // Classes
    @GetMapping("/classes")
    public String listClasses(Model model, @RequestParam(required = false) String keyword) {
        List<SchoolClass> classes = keyword == null ? classService.findAll() : classService.search(keyword);
        model.addAttribute("classes", classes);
        model.addAttribute("keyword", keyword);
        return "admin/classes";
    }

    @GetMapping("/classes/new")
    public String newClass(Model model) {
        model.addAttribute("class", new SchoolClass());
        model.addAttribute("subjects", subjectService.findAll());
        return "admin/class-form";
    }

    @PostMapping("/classes/save")
    public String saveClass(@ModelAttribute SchoolClass classEntity) {
        classService.save(classEntity);
        return "redirect:/admin/classes";
    }

    @GetMapping("/classes/delete/{id}")
    public String deleteClass(@PathVariable Long id) {
        classService.deleteById(id);
        return "redirect:/admin/classes";
    }

    // Subjects
    @GetMapping("/subjects")
    public String listSubjects(Model model, @RequestParam(required = false) String keyword) {
        List<Subject> subjects = keyword == null ? subjectService.findAll() : subjectService.search(keyword);
        model.addAttribute("subjects", subjects);
        model.addAttribute("keyword", keyword);
        return "admin/subjects";
    }

    @GetMapping("/subjects/new")
    public String newSubject(Model model) {
        model.addAttribute("subject", new Subject());
        return "admin/subject-form";
    }

    @PostMapping("/subjects/save")
    public String saveSubject(@ModelAttribute Subject subject) {
        subjectService.save(subject);
        return "redirect:/admin/subjects";
    }

    @GetMapping("/subjects/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteById(id);
        return "redirect:/admin/subjects";
    }

    // Payments
    @GetMapping("/payments")
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentService.findAll());
        model.addAttribute("students", studentService.findAll());
        return "admin/payments";
    }

    @GetMapping("/payments/new")
    public String newPayment(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("students", studentService.findAll());
        return "admin/payment-form";
    }

    @PostMapping("/payments/save")
    public String savePayment(@ModelAttribute Payment payment) {
        payment.setDate(new Date());
        paymentService.save(payment);
        return "redirect:/admin/payments";
    }

    @GetMapping("/payments/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deleteById(id);
        return "redirect:/admin/payments";
    }

    // Schedules
    @GetMapping("/schedules")
    public String listSchedules(Model model) {
        model.addAttribute("schedules", scheduleService.findAll());
        model.addAttribute("classes", classService.findAll());
        return "admin/schedules";
    }

    @GetMapping("/schedules/new")
    public String newSchedule(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("classes", classService.findAll());
        model.addAttribute("subjects", subjectService.findAll());
        return "admin/schedule-form";
    }

    @PostMapping("/schedules/save")
    public String saveSchedule(@ModelAttribute Schedule schedule) {
        scheduleService.save(schedule);
        return "redirect:/admin/schedules";
    }

    @GetMapping("/schedules/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return "redirect:/admin/schedules";
    }
}