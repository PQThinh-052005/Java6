package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/hello.th")
    public String StringHello(Model model) {
        model.addAttribute("message", "FPT <b>Polytechnic</b>");
        return "hello";
    }
    
}
