package com.example.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
    @GetMapping("/index")
    public String index() {
        return "home/index";
    }
    @GetMapping("/about")
    public String about() {
        return "home/about";
    }
}
