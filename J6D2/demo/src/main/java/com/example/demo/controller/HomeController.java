package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;
import java.io.IOException;

import com.example.bean.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
    @RequestMapping("/home/index")
    public String index(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf!");
        ObjectMapper mapper = new ObjectMapper();
        String path = "D:\\VSCode\\Java6\\J6D2\\demo\\src\\main\\resources\\static\\student.json";
        try {
            Student student = mapper.readValue(new File(path), Student.class);
            model.addAttribute("student", student);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error reading student data.");
        }
        return "/home/Index.html";
    }
}
