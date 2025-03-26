package com.example.demo.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bean.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class Student2Controller {
    @RequestMapping("/student2")
    public String student2(Model model, @RequestParam("index") Optional<Integer> index) throws Exception  {
        File file = new ClassPathResource("/static/students.json").getFile();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Student>> typeReference = new TypeReference<List<Student>>(){};
        List<Student> liststudents = mapper.readValue(file, typeReference);
        
        model.addAttribute("ddvs", liststudents);
        model.addAttribute("student", liststudents.get(index.orElse(0)));
        
        return "student2";
    }
}
