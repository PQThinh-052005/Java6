package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
public class ScopeController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpSession session;
    @Autowired
    ServletContext context;

    @RequestMapping("/scope")
    public String Hello(Model model) {
        model.addAttribute("a", "i am in model");
        request.setAttribute("b", "i am in request");
        session.setAttribute("c", "i am in session");
        context.setAttribute("d", "i am in context");
        return "scope";
    }
}
