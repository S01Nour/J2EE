package com.university.controller;

import com.university.model.*;
import com.university.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    private PaymentService paymentService;

    @Autowired
    private ScheduleService scheduleService;

    // Students
    @GetMapping("/students")
    public String listStudents(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Student> students = studentService.search(keyword);
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
        Student student = studentService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student ID"));
        model.addAttribute("student", student);
        return "admin/student-form";
    }

    @PostMapping("/students/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
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
    public String listClasses(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Class> classes = classService.search(keyword);
        model.addAttribute("classes", classes);
        model.addAttribute("keyword", keyword);
        return "admin/classes";
    }

    @GetMapping("/classes/new")
    public String newClass(Model model) {
        model.addAttribute("classEntity", new Class());
        return "admin/class-form";
    }

    @GetMapping("/classes/edit/{id}")
    public String editClass(@PathVariable Long id, Model model) {
        Class classEntity = classService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid class ID"));
        model.addAttribute("classEntity", classEntity);
        return "admin/class-form";
    }

    @PostMapping("/classes/save")
    public String saveClass(@Valid @ModelAttribute("classEntity") Class classEntity, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/class-form";
        }
        classService.save(classEntity);
        return "redirect:/admin/classes";
    }

    @GetMapping("/classes/delete/{id}")
    public String deleteClass(@PathVariable Long id) {
        classService.deleteById(id);
        return "redirect:/admin/classes";
    }

    // Payments
    @GetMapping("/payments")
    public String listPayments(Model model) {
        List<Payment> payments = paymentService.findAll();
        model.addAttribute("payments", payments);
        model.addAttribute("students", studentService.findAll());
        return "admin/payments";
    }

    @GetMapping("/payments/new")
    public String newPayment(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("students", studentService.findAll());
        return "admin/payment-form";
    }

    @GetMapping("/payments/edit/{id}")
    public String editPayment(@PathVariable Long id, Model model) {
        Payment payment = paymentService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid payment ID"));
        model.addAttribute("payment", payment);
        model.addAttribute("students", studentService.findAll());
        return "admin/payment-form";
    }

    @PostMapping("/payments/save")
    public String savePayment(@Valid @ModelAttribute("payment") Payment payment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.findAll());
            return "admin/payment-form";
        }
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
        List<Schedule> schedules = scheduleService.findAll();
        model.addAttribute("schedules", schedules);
        model.addAttribute("classes", classService.findAll());
        return "admin/schedules";
    }

    @GetMapping("/schedules/new")
    public String newSchedule(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("classes", classService.findAll());
        return "admin/schedule-form";
    }

    @GetMapping("/schedules/edit/{id}")
    public String editSchedule(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid schedule ID"));
        model.addAttribute("schedule", schedule);
        model.addAttribute("classes", classService.findAll());
        return "admin/schedule-form";
    }

    @PostMapping("/schedules/save")
    public String saveSchedule(@Valid @ModelAttribute("schedule") Schedule schedule, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("classes", classService.findAll());
            return "admin/schedule-form";
        }
        scheduleService.save(schedule);
        return "redirect:/admin/schedules";
    }

    @GetMapping("/schedules/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteById(id);
        return "redirect:/admin/schedules";
    }
}