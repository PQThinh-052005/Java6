package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bean.Student2;

@Controller
public class Student2Controller {
    @GetMapping("/validation/form")
    public String form(Model model) {
        Student2 student2 = new Student2();
        model.addAttribute("sv", student2);
        return "form2";
    }

    @PostMapping("/validation/validated")
    public String result(Model model, @Validated @ModelAttribute("sv") Student2 student2, Errors errors) {
        if (errors.hasErrors()) {

            model.addAttribute("message", "Vui lòng sửa các lỗi sau đây!");
            return "form2"; // Quay lại form nếu có lỗi
        }
        return "success"; // Chỉ vào success nếu không có lỗi
    }

}
