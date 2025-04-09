package com.example.httpbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

	@RequestMapping("/home/index")
	public String index(Model model) {
		model.addAttribute("message", "This is home page");
		return "home/index";
	}

	@RequestMapping("/home/about")
	public String about(Model model) {
		model.addAttribute("message", "This is introduction page");
		return "home/index";
	}

	@RequestMapping("/loginform")
	public String login() {
		return "home/login";
	}

	@RequestMapping("/home/admin")
	public String admin(Model model) {
		model.addAttribute("message", "This is admin page");
		return "home/index";
	}
	@RequestMapping("/home/user") 
	public String user(Model model) {
		model.addAttribute("message", "This is user page");
		return "home/index";
	}
	@RequestMapping("/home/guest")
	public String guest(Model model) {
		model.addAttribute("message", "This is guest page");
		return "home/index";
	}


}
