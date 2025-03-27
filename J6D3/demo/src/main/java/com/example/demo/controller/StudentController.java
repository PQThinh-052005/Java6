package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.Student;

@Controller
public class StudentController {
    @GetMapping("/student/form")
    public String form(Model model) {
        Student student = new Student();
        model.addAttribute("sv", student);
        return "form";

    }

    @PostMapping("/student/save")
    public String save(@ModelAttribute("sv") Student form) {
        return "success";
    }
}
