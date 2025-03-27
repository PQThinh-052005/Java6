package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class fragContrller {
    @RequestMapping("/frag")
    public String frag() {
        return "page";
    }
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    
}
