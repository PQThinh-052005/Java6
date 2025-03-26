package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.io.File;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bean.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class StudentController {
    @RequestMapping("/student")
    public String student(Model model, @RequestParam("index") Optional<Integer> index) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String path = "D:\\\\VSCode\\\\Java6\\\\J6D2\\\\demo\\\\src\\\\main\\\\resources\\\\static\\\\students.json";
        TypeReference<List<Student>> typeReference = new TypeReference<List<Student>>(){};
        try {
             List<Student> students = mapper.readValue(new File(path), typeReference);
             Student student = students.get(index.orElse(0));
             model.addAttribute("student", student);
             model.addAttribute("index", index.orElse(0));
        } catch (IOException e) {
            // TODO: handle exception
        }
       

        
        return "student";
    }
}
